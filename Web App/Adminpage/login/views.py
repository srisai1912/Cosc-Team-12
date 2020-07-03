from django.shortcuts import render
from django.http import HttpResponse,HttpResponseRedirect
import requests
import json

# Create your views here.

def login(request):
    
    #return HttpResponse("hello wassup")
    return render(request,'login.html')
def register(request):
    return render(request,'register.html')

def options(request):
    username=request.POST['email']
    password=request.POST['pass']
    if username == "Admin@cosc.com" and password == "password" :
        return render(request,'options.html')
    else:
        return render(request,'login.html')
def optionsp(request):
    return render(request,'options.html')
def upload(request):
    return render(request,'upload.html')

def sendvac(request):
    idi=id('i')
    idstr=str(idi)
    deptname1=request.POST['department']
    position1=request.POST['position']
    reqqual1=request.POST['Qualification']
    percentage1=request.POST['percentage']
    vac = { 'vacant_roll_id':idstr,'Dept_name':deptname1,'Position_vacant':position1,'Required_quali':reqqual1,'percentage':percentage1}
    r=requests.post('https://admintesting.herokuapp.com/addvacantroles',vac)
    if(r.status_code==201):
        return render(request, 'options.html', {'alert_flag': True})
    else:
        return render(request, 'upload.html', {'alert_flag': True})   
def viewvac(request):
    #vac={}
    vac=requests.get('https://admintesting.herokuapp.com/seevacanciesadmin')
    #return HttpResponse(vac.text)
    return render(request,'viewvac.html',{'vacs': vac.json()})

def select(request):
    applicants=requests.get('https://admintesting.herokuapp.com/seedetails','1')
    #return HttpResponse(applicants.text)
    i=1
    #global slist
    """slist=request.POST.getlist('statuslist')
    alist=request.POST.getlist('applist')"""
    apps=applicants.json()
    #Application_id=apps.Application_id
    """for app in apps :
        stat={ 'Application_id':alist[i],'id_Status':slist[i]}
        i+=1
    r=requests.post('https://admintesting.herokuapp.com/writestatus',stat)
    if(r.status_code==201):
        return render(request, 'options.html', {'alert_flag': True})
    else:
        return render(request, 'select.html', {'alert_flag': True}) """ 
    return render(request,'select.html',{'apps':apps})
    #return HttpResponse(alist)
def recruitedfaculty(request):
    recruited_data=requests.get('https://admintesting.herokuapp.com/checkfaculty','1')
    return render(request,'recruitedfaculty.html', {'recruited_data':recruited_data.json()})

def pselect(request):
    slist=request.POST.getlist('statuslist')
    #alist=request.POST.getlist('Applist')
    #i=0
    applicants=requests.get('https://admintesting.herokuapp.com/seedetails','1')
    apps=applicants.json()
    n=len(slist)
    alist=[None]*n 
    elist=[None]*n
    rlist=[None]*n
    i=0
    while i < n :
        alist[i]=apps[i]['Application_id']
        elist[i]=apps[i]['EmailId']
        rlist[i]=apps[i]['Roll_id']
        i+=1
    i=0
    while i < n : ####This is for Writing Status
        if slist[i]!="RECIEVED " :
            stat={'Application_id':alist[i],'id_Status':slist[i]}
            r=requests.post('https://admintesting.herokuapp.com/writestatus',stat)
        i+=1
    i=0
    while i < n : ####This is for sending values to Recruited Faculty table
        if slist[i]=="Accepted" :
            stati={'EmailId':elist[i],'Roll_id':rlist[i]}
            r=requests.post('https://admintesting.herokuapp.com/recruited',stati)
        i+=1
    if(r.status_code==200):
        return render(request, 'options.html', {'alert_flag': True})
    else:
        return render(request, 'select.html', {'alert_flag': True})
    #return HttpResponse(alist)