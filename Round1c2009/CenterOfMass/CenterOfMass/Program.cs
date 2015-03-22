using System;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Security.Policy;

namespace CenterOfMass
{
    public class Program
    {

        struct vec3
        {
            public double x, y, z;
        }

        public static void Main(string[] args)
        {
            StreamReader lines = new StreamReader(args[0]);
            var writer = new StreamWriter("./result");
            var nbTest = Convert.ToInt32(lines.ReadLine());

            for (var i = 0; i < nbTest; i++)
            {
                var nbFirefly = int.Parse(lines.ReadLine());
                vec3 Pos = new vec3();
                vec3 Vel = new vec3();
                foreach (int indexFirefly in Enumerable.Range(0, nbFirefly))
                {
                    string[] firefly = lines.ReadLine().Split(' ');
                    Pos.x += double.Parse(firefly[0]) / nbFirefly;
                    Pos.y += double.Parse(firefly[1]) / nbFirefly;
                    Pos.z += double.Parse(firefly[2]) / nbFirefly;

                    Vel.x += double.Parse(firefly[3]) / nbFirefly;
                    Vel.y += double.Parse(firefly[4]) / nbFirefly;
                    Vel.z += double.Parse(firefly[5]) / nbFirefly;

                }
                Console.Write("Pos: {0},{1},{2} \n Vel: {3},{4},{5} \n",
                                        Pos.x, Pos.y, Pos.z,
                                        Vel.x, Vel.y, Vel.z);

                double dis = Math.Sqrt(Pos.x * Pos.x + Pos.y * Pos.y + Pos.z * Pos.z);
                int temp = 1;
                for (int t = 1; t < 10000000; t++)
                {
                    Pos.x = Pos.x + t * Vel.x;
                    Pos.y = Pos.y + t * Vel.y;
                    Pos.z = Pos.z + t * Vel.z;

                    double dis2 = Math.Sqrt(Pos.x * Pos.x + Pos.y * Pos.y + Pos.z * Pos.z);
                    if (dis2 < dis)
                    {

                        dis = dis2;
                        temp = t;
                        if (dis < .00000001)
                        {
                            dis = 0;
                            break;
                        }
                    }

                    else
                    {
                        break;
                    }


                }
                Console.Write("DIs: {0} \n", dis);


                Console.Write("Case #{0}: {1:f8} {2:f8}\n", i + 1, dis, temp);
                writer.Write("Case #{0}: {1:f8} {2:f8}\n", i + 1, dis, temp);
            }
            // Console.Read();
            writer.Close();
        }

        private static string Answer(string line)
        {
            return "";
        }
    }
}