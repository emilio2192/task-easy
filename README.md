
# Documentation 
The code is built on java spring boot with JAVA 8.

### **Host:** http://localhost:8080/available

### **Headers:** `Content-Type: application/json`

### **Method:** `POST`

**Data accepted (JSON):**

```[
    {
        "name":"name of employee",
        "schedule":[
            "hour in format (24hrs - is the hour for start meeting)",
            "08:00",
            "09:00"
        ] 
    },
    {
        "name":"name of employee",
        "schedule":[
            "hour in format (24hrs - is the hour for start meeting)",
            "08:00",
            "09:00"
        ] 
    },
]```

**Data returned (JSON):**
```[
  {
    "start": "08:00:00",
    "end": "08:30:00",
    "persons": [
      "Emilio",
      "Luis",
      "Juan"
    ]
  },
]```

