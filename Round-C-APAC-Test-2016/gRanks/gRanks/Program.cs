using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gRanks
{
    class Program
    {
        public static Dictionary<string, IList<int>> playerScores = new Dictionary<string, IList<int>>();

        static void Main(string[] args)
        {
            using (TextReader reader = File.OpenText("A-large-practice.in"))
            {
                using (var writer = new StreamWriter("anwser"))
                {

                    var line = reader.ReadLine();
                    var caseCount = int.Parse(line);
                    for (int i = 1; i <= caseCount; i++)
                    {
                        line = reader.ReadLine();
                        var placeAwardedCount = int.Parse(line);

                        line = reader.ReadLine();
                        var placeAwards = Array.ConvertAll(line.Split(' '), int.Parse);

                        line = reader.ReadLine();
                        var competitionCount = int.Parse(line);

                        playerScores = new Dictionary<string, IList<int>>();

                        for (int j = 0; j < competitionCount; j++)
                        {
                            line = reader.ReadLine();
                            var splitedLine = line.Split(' ');
                            var competitionValue = int.Parse(splitedLine[0]);

                            for (int k = 1; k <= placeAwardedCount; k++)
                            {
                                if (!playerScores.ContainsKey(splitedLine[k]))
                                {
                                    playerScores.Add(splitedLine[k], new List<int>());
                                }

                                playerScores[splitedLine[k]].Add(competitionValue * placeAwards[k - 1]);
                            }
                        }

                        line = reader.ReadLine();
                        var scoreBound = int.Parse(line);

                        var podium = new List<dynamic>();
                        foreach (var playerScore in playerScores)
                        {
                            var boundedScore = playerScore.Value.OrderByDescending(x => x).Take(scoreBound).Sum();
                            

                            podium.Add(new { Score = boundedScore, Name = playerScore.Key });
                        }


                        //Console.WriteLine("Case #" + i + ":");
                        writer.Write("Case #" + i + ":\n");

                        var placeForScore = new Dictionary<int, int>();
                        var place = 1;
                        foreach (var player in podium.OrderByDescending(x => x.Score).ThenBy(x => x.Name))
                        {
                            if (!placeForScore.ContainsKey(player.Score))
                            {
                                placeForScore.Add(player.Score, place);
                            }
                            //Console.WriteLine(placeForScore[player.Score] + ": " + player.Name);
                            writer.Write(placeForScore[player.Score] + ": " + player.Name + "\n");
                            place++;
                        }

                       // Console.ReadLine();
                    }
                }
            }
        }
    }
}
