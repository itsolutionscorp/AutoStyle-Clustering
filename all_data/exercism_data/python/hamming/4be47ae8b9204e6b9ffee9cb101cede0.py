#---------------------------------------------
# Name:        mcface3000
# Purpose:      Ham it up
#---------------------------------------------

def distance(variable, variable2):
    #Set the counter to zero, so the while loop knows when to stop
    counter = 0
    #Set the distance to zero
    distance = 0
    #Tells python to loop the following code until counter is equal to the length of variable
    distance = 0
    for i in range(0, len(variable)):
        if variable[i] != variable2[i]:
            distance += 1
    #This is usually the part that gets me.  Remembered to return distance out of the loop
    return distance
