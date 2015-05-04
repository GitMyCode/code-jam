using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace standing_ovation
{
    class Program
    {
        static void Main(string[] args)
        {
            //StreamReader sr = new StreamReader("./sample.txt");
            StreamReader sr = new StreamReader("./A-large-practice.in");
            //StreamReader sr = new StreamReader("./A-small-practice.in");
            StreamWriter sw = new StreamWriter("./result");
            int nbTest = int.Parse(sr.ReadLine());
            foreach (var test in Enumerable.Range(1, nbTest))
            {
                var line = sr.ReadLine().Split(' ');

                var audience = line[1].ToCharArray().Select(x => (int)x - '0').ToArray();
                var ans = Solver(audience);
                Console.Write("Case #{0}: {1}\n", test, ans);
                sw.Write("Case #{0}: {1}\n", test, ans);
            }
            sr.Close();
            sw.Close();
            Console.Read();
        }


        public static int Solver(int[] audience)
        {
            var shynessLvlCanStand = 0;
            var friendNeeded = 0;
            for (int i = 0; i < audience.Length; i++)
            {
                var nbPerson = audience[i];
                if (nbPerson!= 0 && i > shynessLvlCanStand)
                {
                    friendNeeded += (i - shynessLvlCanStand);
                    shynessLvlCanStand += (i - shynessLvlCanStand);
                }
                shynessLvlCanStand += nbPerson;
            }

            return friendNeeded;
        }

    }

}
