package org.lantern;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lantern.data.Dao;
import org.lantern.data.Dao.DbCall;
import org.lantern.data.LanternUser;
import org.lantern.friending.Friending;
import org.lantern.loggly.LoggerFactory;

import com.googlecode.objectify.Objectify;

/**
 * This task increases the invites for the untrusted user identified by the
 * param userId, decreasing that user's degree by the param degreeDelta.
 */
@SuppressWarnings("serial")
public class IncreaseUntrustedInvites extends HttpServlet {

    private static final transient Logger LOGGER = LoggerFactory
            .getLogger(IncreaseUntrustedInvites.class);
    
    public static final String USER_ID = "userId";
    public static final String DEGREE_DELTA = "degreeDelta";

    @Override
    public void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException {
        final String userId = request.getParameter(USER_ID);
        final int degreeDelta = Integer.parseInt(request.getParameter(DEGREE_DELTA));
        LOGGER.info("Increasing invites for user: " + userId);
        Dao dao = new Dao();
        dao.withTransaction(new DbCall<Void>() {
            @Override
            public Void call(Objectify ofy) {
                LanternUser user = ofy.find(LanternUser.class, userId);
                int newDegree = Math.max(1, user.getDegree() - degreeDelta);
                user.setDegree(newDegree);
                ofy.put(user);
                Friending.recalculateQuota(ofy, userId, newDegree);
                return null;
            }
        });
    }

}
