/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	 Instant start=tweets.get(0).getTimestamp();
    	 Instant end=tweets.get(0).getTimestamp();
       for(int i=0;i<tweets.size();i++)
       {
    	   if(tweets.get(i).getTimestamp().isAfter(end));
    		end=tweets.get(i).getTimestamp();
    	   if(tweets.get(i).getTimestamp().isBefore(start))
    		 start=tweets.get(i).getTimestamp();   
       }
        Timespan time=new Timespan(start,end);
        return time;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	Set<String> name=new HashSet<String>();
    	for(int i=0;i<tweets.size();i++)
        {
    		String[] temp=tweets.get(i).getText().split(" ");
     	   for(int j=0;j<temp.length;j++)
     	   {
     		   for(int k=0;k<tweets.size();k++)
     		   {
     			   if(temp[j].equalsIgnoreCase('@'+tweets.get(k).getAuthor()))
     				   name.add(tweets.get(k).getAuthor());
     		   }
     	   }
        }
    	
    	return name;
    }

}
