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


            var lines = File.ReadLines("./A-large-practice.in").ToList();
            var writer = new System.IO.StreamWriter("./result");

            int nbTest =  Convert.ToInt32(lines.First());
            lines.RemoveAt(0);
            for (int i = 0; i < nbTest; i++)
            {
                string ans = Answer(lines[i]);
                writer.Write("Case #{0}: {1}\n",i+1,ans);
 
            }

            writer.Close();

            Console.Read();

        }

        static string Answer(string line)
        {
            string ans = "";
            DicDigits.Clear();
            var nbDifferent = 0;
            long  resultBase10 = 0;
            foreach (char c in line)
            {
                if (DicDigits.ContainsKey(c))
                {
                }
                else
                {
                    if (DicDigits.Keys.Count == 0)
                    {
                        DicDigits[c] = 1;
                    }else if (DicDigits.Keys.Count() == 1)
                    {
                        DicDigits[c] = 0;
                    }
                    else
                    {
                        DicDigits[c] = nbDifferent;
                    }
                    nbDifferent++;
                }
            }
            if (nbDifferent <= 1)
            {
                nbDifferent = 2;
            }           
            for(int i= 0; i < line.Length; i++)
            {
                int val = DicDigits[line[i]];
                int exposant = line.Length - i-1;

                resultBase10 = resultBase10*nbDifferent + val;

            }

            ans = resultBase10.ToString();

            return ans;
        }

    }
}
