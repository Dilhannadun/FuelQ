using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace backend.Models
{
    public class Queue
    {
        public string FuelType { get; set; }

        public int NumberofVehicles { get; set; }

        public int NumberofVehiclesOut { get; set; }

        public float TotalWaitTime { get; set; }
    }
}