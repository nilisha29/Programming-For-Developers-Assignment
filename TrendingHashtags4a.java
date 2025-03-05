// Problem Description:
// You need to find the top 3 trending hashtags from tweets posted in February 2024. Each tweet can contain multiple hashtags, and the task 
// is to count their occurrences, sort them by frequency in descending order, and if counts are tied, sort them alphabetically.

// Objective:
// Identify the top 3 hashtags in February 2024 based on their frequency of occurrence in the tweet text.

// Approach:
// 1) Extract Data: Filter tweets from February 2024.
// 2) Regex: Use a regular expression to extract hashtags from the tweet text.
// 3) Count Hashtags: Use a HashMap to count occurrences of each hashtag.
// 4) Sort and Limit: Sort hashtags by frequency (descending) and alphabetically for ties. Limit the result to the top 3.
// 5) Output: Print the top 3 hashtags and their counts in a formatted table.


import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

class TrendingHashtags4a {
    public static void main(String[] args) {
        List<Tweet> tweets = Arrays.asList(
            new Tweet(135, 13, "Enjoying a great start to the day. #HappyDay #MorningVibes", "2024-02-01"),
            new Tweet(136, 14, "Another #HappyDay with good vibes! #FeelGood", "2024-02-03"),
            new Tweet(137, 15, "Productivity peaks! #WorkLife #ProductiveDay", "2024-02-04"),
            new Tweet(138, 16, "Exploring new tech frontiers. #TechLife #Innovation", "2024-02-04"),
            new Tweet(139, 17, "Gratitude for today's moments. #HappyDay #Thankful", "2024-02-05"),
            new Tweet(140, 18, "Innovation drives us. #TechLife #FutureTech", "2024-02-07"),
            new Tweet(141, 19, "Connecting with nature's serenity. #Nature #Peaceful", "2024-02-09")
        );

        findTopTrendingHashtags(tweets);
    }

    public static void findTopTrendingHashtags(List<Tweet> tweets) {
        Map<String, Integer> hashtagCount = new HashMap<>();
        Pattern hashtagPattern = Pattern.compile("#\\w+");

        for (Tweet tweet : tweets) {
            if (tweet.date.startsWith("2024-02")) { // Only consider tweets from February 2024
                Matcher matcher = hashtagPattern.matcher(tweet.text);
                while (matcher.find()) {
                    String hashtag = matcher.group();
                    hashtagCount.put(hashtag, hashtagCount.getOrDefault(hashtag, 0) + 1);
                }
            }
        }

        // Sort hashtags first by count in descending order, then alphabetically
        List<Map.Entry<String, Integer>> sortedHashtags = hashtagCount.entrySet()
            .stream()
            .sorted((a, b) -> 
                b.getValue().equals(a.getValue()) ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()
            )
            .limit(3) // Get top 3 hashtags
            .collect(Collectors.toList());

        System.out.println("+------------+-------+");
        System.out.println("| hashtag    | count |");
        System.out.println("+------------+-------+");
        for (Map.Entry<String, Integer> entry : sortedHashtags) {
            System.out.printf("| %-10s | %5d |\n", entry.getKey(), entry.getValue());
        }
        System.out.println("+------------+-------+");
    }
}

class Tweet {
    int userId;
    int tweetId;
    String text;
    String date;

    public Tweet(int userId, int tweetId, String text, String date) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.text = text;
        this.date = date;
    }
}

//Output
//hashtag    | count |
// +------------+-------+
// | #HappyDay  |     3 |
// | #TechLife  |     2 |
// | #FeelGood  |     1 |
