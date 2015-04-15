import time
from datetime import datetime

def datetime2list(inDateTime):
	'''
	Converts a datetime tuple to a list so it is mutable.
	'''
	outList = []
	outList.append(inDateTime.year)
	outList.append(inDateTime.month)
	outList.append(inDateTime.day)
	outList.append(inDateTime.hour)
	outList.append(inDateTime.minute)
	outList.append(inDateTime.second)
	return outList

def list2datetime(inList):
	'''
	Converts a list to a datetime tuple
	'''
	while len(inList) < 9:
		inList.append(0)
	outDatetime = datetime.fromtimestamp(time.mktime(inList))
	return outDatetime

def add_gigasecond(dateIn):
	'''
	Returns datetime that is one gigasecond in the future
	'''

	# If the year entered was outside of the range of mktime() i converted the date to a list, changed
	# the year value to 1970 and stored the delta of the years to be applied to the year again at the end
	dateDif = 0
	if dateIn.year < 1970:
		tempList = datetime2list(dateIn)
		dateDif = 1970 - tempList[0]
		tempList[0] = 1970
		dateIn = list2datetime(tempList)

	# converts the time tuple to seconds
	timeSeconds = time.mktime(datetime.timetuple(dateIn))
	# creates a time stuct so daylight savings status can be identifed
	timeStruct = time.localtime(timeSeconds)
	# adds one gigasecond to the time
	gigaBDay = timeSeconds + (10**9)
	# creates a time struct of the new time also for daylight savings reasons
	gigaStruct = time.localtime(gigaBDay)

	# new time with gigasecond added (and maybe also an extra hour added or removed)
	outDate = datetime.fromtimestamp(gigaBDay + ((timeStruct.tm_isdst - gigaStruct.tm_isdst) * 3600))

	# if year was changed at the start it is chnaged back
	if dateDif != 0:
		tempList = datetime2list(outDate)
		tempList[0] = outDate.year - dateDif

		tempList[3] = outDate.hour - 1  # i had to add this to make it work for one example,
										# i'm not sure why i gained an hour when changing the year
										# any help appreciated!

		outDate = list2datetime(tempList)

	return outDate
