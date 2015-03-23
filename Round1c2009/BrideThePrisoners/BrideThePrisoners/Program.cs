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

        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader("./sample");
            int nbTest = int.Parse(sr.ReadLine());
            foreach (var test in Enumerable.Range(1, nbTest))
            {
                var l = (sr.ReadLine()).Split(' ');
                int nbPrisoners = int.Parse(l[0]);
                int nbToRelease = int.Parse(l[1]);

                var l2 = sr.ReadLine().Split(' ');
                int[] allPrisonerReleased = Array.ConvertAll(l2, int.Parse);

                int ans = MinToBride(nbPrisoners, nbToRelease, allPrisonerReleased);

                Console.Write("Case #{0}: \n", test);
            }
            Console.Read();

        }

        static int MinToBride(int nbPrisoners, int nbToRelease, int[] allPrisonerReleased)
        {



            return 0;
        }

        static int FreePrisoner(int left, int rigth, int index, int[] allP)
        {
            for (int i = 0; i < allP.Length; i++)
            {
                int cost = (rigth - left);
                int indexPrisoner = allP[i];
                cost += FreePrisoner(indexPrisoner, rigth, i, allP) + FreePrisoner(left, indexPrisoner, i, allP);
                min = Math.Min(min, cost);


            }
            return min;
        }
    }
}
