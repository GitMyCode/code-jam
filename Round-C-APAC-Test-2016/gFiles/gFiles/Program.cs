// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Program.cs" company="">
//   
// </copyright>
// <summary>
//   The program.
// </summary>
// --------------------------------------------------------------------------------------------------------------------



using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gFiles
{
    /// <summary>
    /// The program.
    /// </summary>
    internal class Program
    {
        /// <summary>
        /// The main.
        /// </summary>
        /// <param name="args">
        /// The args.
        /// </param>
        private static void Main(string[] args)
        {
            using (var reader = File.OpenText("B-large-practice.in"))
            {
                using (var writer = new StreamWriter("answer"))
                {
                    var line = reader.ReadLine();
                    var caseCount = int.Parse(line);
                    for (int caseNumber = 1; caseNumber <= caseCount; caseNumber++)
                    {
                        line = reader.ReadLine();
                        var updateStatusCount = int.Parse(line);

                        var ranges = new List<dynamic>();
                        for (int i = 0; i < updateStatusCount; i++)
                        {
                            var splitedLine = reader.ReadLine().Split(' ');
                            var percent = ulong.Parse(splitedLine[0]);
                            var fileCount = ulong.Parse(splitedLine[1]);
                            if (percent == 100)
                            {
                                ranges.Clear();
                                ranges.Add(new { lower = (ulong)fileCount, higher = (ulong)fileCount });

                            }
                            else if (percent == 0)
                            {

                            }
                            else
                            {
                                ulong lowerRange = ((fileCount * 100) / (percent + 1)) + 1;
                                ulong higherRange = (fileCount * 100) / percent;
                                ranges.Add(new { lower = (ulong)lowerRange, higher = (ulong)higherRange });
                            }

                        }

                        var low = ulong.MinValue;
                        var top = ulong.MaxValue;
                        // Console.WriteLine("\n---");
                        //  Console.WriteLine("Case: " + caseNumber);
                        foreach (var range in ranges)
                        {
                            //    Console.WriteLine("low: " + range.lower + "  high: " + range.higher);
                            if (low < range.lower)
                            {
                                low = range.lower;
                            }

                            if (top > range.higher)
                            {
                                top = range.higher;
                            }
                        }

                        double fileTotal = low;
                        if (!low.Equals(top))
                        {
                            writer.Write("Case #" + caseNumber + ": -1\n");
                        }
                        else
                        {
                            Console.WriteLine("Case #" + caseNumber + ": " + fileTotal);
                            writer.Write("Case #" + caseNumber + ": " + fileTotal + "\n");
                        }
                    }

                    //       Console.ReadLine();
                }
            }
        }
    }
}