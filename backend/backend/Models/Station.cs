using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace backend.Models
{
    public class Station
    {
        public string Id { get; set; }

        public string StationName { get; set; }

        public string City { get; set; }

        public float Latitude { get; set; }

        public float Longitude { get; set; }

        public float Petrol95Qty { get; set; }

        public float Petrol92Qty { get; set; }

        public float DieselQty { get; set; }

        public Queue[] Queues { get; set; }
    }
}