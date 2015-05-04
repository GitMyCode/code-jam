using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BrideThePrisoners
{
    class Program
    {
        private static int min = int.MaxValue;
        private static int nbPrisoners = 0;
        private static int nbToRelease = 0;
        private static IList<int> allPrisonersToRelease;


        private static int[,] memo = new int[10002, 10002];

        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader("./C-large-practice.in");
            StreamWriter sw = new StreamWriter("./result");
            int nbTest = int.Parse(sr.ReadLine());
            foreach (var test in Enumerable.Range(1, nbTest))
            {
                min = int.MaxValue;
                memo = new int[10002, 10002];
                var readLine = sr.ReadLine();
                if (readLine != null)
                {
                    var l = readLine.Split(' ');
                    nbPrisoners = int.Parse(l[0]);
                    nbToRelease = int.Parse(l[1]);
                }

                var line = sr.ReadLine();
                if (line != null)
                {
                    var l2 = line.Split(' ');
                    allPrisonersToRelease = Array.ConvertAll(l2, int.Parse);
                }
                allPrisonersToRelease = allPrisonersToRelease.OrderBy(o => o).ToList();
                int ans = MinToBride(nbPrisoners);

                Console.Write("Case #{0}: {1}\n", test, ans);
                sw.Write("Case #{0}: {1}\n", test, ans);
            }
            sr.Close();
            sw.Close();
            Console.Read();
        }

        static int MinToBride(int nbPrisoners)
        {
            return FreePrisoner(1, nbPrisoners);
        }

        static int FreePrisoner(int left, int rigth)
        {
            if (memo[left, rigth] != 0)
                return memo[left, rigth];

            int minHere = int.MaxValue;
            var ranged = allPrisonersToRelease.Where(x => x >= left && x <= rigth).ToArray();
            if (ranged.Length == 0)
            {
                return 0;
            }

            foreach (int prisonersInder in ranged)
            {

                int indexPrisoner = prisonersInder;
                int cost = (rigth - left);

                cost += FreePrisoner(left, indexPrisoner - 1) + FreePrisoner(indexPrisoner + 1, rigth);
                minHere = Math.Min(cost, minHere);
            }

            memo[left, rigth] = minHere;
            return minHere;
        }
    }
}
