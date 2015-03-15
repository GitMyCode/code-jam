using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Linq.Expressions;
using System.Security.AccessControl;
using System.Text;
using System.Threading.Tasks;

namespace AllYourBase
{
    class Program
    {
        private static string allDigits = "0123456789abcdefghijklmnopqrstuvwxyz";
        static Dictionary<char, int> DicDigits = new Dictionary<char, int>();
        static void Main(string[] args)
        {


            var lines = File.ReadLines("./sample.txt");
            foreach (var line in lines)
            {
                string ans = Answer(line);
                Console.WriteLine(ans);
            }


            Console.Read();

        }

        static string Answer(string line)
        {
            DicDigits.Clear();
            var nbDifferent = 0;
            foreach (char c in line)
            {
                if (DicDigits.ContainsKey(c))
                {
                }
                else
                {
                    DicDigits[c] = nbDifferent++;
                }
            }


            return "";
        }

    }
}
