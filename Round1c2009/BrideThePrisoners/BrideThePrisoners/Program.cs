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


        private static int[,] memo = new int[100,100];

        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader("./sample");
            int nbTest = int.Parse(sr.ReadLine());
            foreach (var test in Enumerable.Range(1, nbTest))
            {
                min = int.MaxValue;
                memo = new int[100, 100];
                var l = (sr.ReadLine()).Split(' ');
                nbPrisoners = int.Parse(l[0]);
                nbToRelease = int.Parse(l[1]);

                var l2 = sr.ReadLine().Split(' ');
                allPrisonersToRelease = Array.ConvertAll(l2, int.Parse);
                allPrisonersToRelease = allPrisonersToRelease.OrderBy(o => o).ToList();
                int ans = MinToBride(nbPrisoners, nbToRelease);

                Console.Write("Case #{0}: {1}\n", test, ans);
            }
            Console.Read();
        }

        static int MinToBride(int nbPrisoners, int nbToRelease)
        {
            return FreePrisoner(1, nbPrisoners, 0);
        }

        static int FreePrisoner(int left, int rigth, int index)
        {
            if (memo[left, rigth] != 0)
                return memo[left, rigth];

            int minHere = int.MaxValue;
            var ranged = allPrisonersToRelease.Where(x => x > left && x < rigth).ToArray();
            if (ranged.Length == 0)
            {
                return 0;
            }

            foreach (int prisonersInder in ranged)
            {

                int indexPrisoner = prisonersInder;
                int cost = (rigth - left);

                cost += FreePrisoner(left, indexPrisoner, 1) + FreePrisoner(indexPrisoner, rigth , 1);
                minHere = Math.Min(cost, minHere);
            }

            //memo[left, rigth] = min;
            return minHere;
        }
    }
}
