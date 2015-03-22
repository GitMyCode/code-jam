using System;
using CenterOfMass;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests
{
    [TestClass]
    public class UnitTest
    {
        [TestMethod]
        public void TestSampleData()
        {
            Program.Main(new string[] { "./test" });


        }
        [TestMethod]
        public void TestSimpleData()
        {
            Program.Main(new string[] { "./B-small-practice.in" });


        }

    }
}
