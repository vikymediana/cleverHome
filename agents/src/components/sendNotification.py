# -*- coding: utf-8 -*-
from urllib2 import *
import urllib
import json
import sys

MY_API_KEY="AIzaSyD7nMq1-MZsX8X9nfidj_wwVoW-jttwqhY"
messageTitle = "Intrusión detectada"
messageBody = "Revise las imágenes de su inmueble"

data={
    "to" : "/topics/myhome",
    "notification" : {
        "body" : messageBody,
        "title" : messageTitle,
        "icon" : "ic_cloud_black_24dp"
    }
}

dataAsJSON = json.dumps(data)

request = Request(
    "https://gcm-http.googleapis.com/gcm/send",
    dataAsJSON,
    { "Authorization" : "key="+MY_API_KEY,
      "Content-type" : "application/json"
    }
)

print urlopen(request).read()
