using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CenterOfMass
{
    class Program
    {
        static void Main(string[] args)
        {
            var lines = File.ReadLines("./test").ToList();
            var writer = new StreamWriter("./result");
            int nbTest = Convert.ToInt32(lines.First());
            lines.RemoveAt(0);

            for (int i = 0; i < nbTest; i++)
            {
                string ans = Answer(lines[i]);
                Console.Write("Case #{0}: {1}\n", i + 1, ans);
                writer.Write("Case #{0}: {1}\n", i + 1, ans);
            }
            Console.Read();
            writer.Close();

        }

        static string Answer(string line)
        {
            return "";
        }
    }
}
