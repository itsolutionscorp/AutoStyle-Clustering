#
# Skeleton file for the Python "Bob" exercise.
#

#still figuring out how to test my code


def hey(what):
    x=''

    remove = ['\n','\t']
    
    for n in remove:
        what = what.replace(n,'')
    
    #print 'this is what %r'% what
    if what=='':
        x= 'Fine. Be that way!'
        return  x
    if what.isupper():
        x= 'Whoa, chill out!'
        return x

        
    for n in range(len(what)):

        #print n
        
        
        

        if not what[len(what)-1-n].isspace():
            #print 'afgadfg'
            if what[len(what)-1-n]=='?':
                x = 'Sure.'
                return x
            else:
                x = 'Whatever.'
                return x
        if what[len(what)-1-n].isspace():
            #print n, len(what)
            if n == len(what)-1:
                
                x= 'Fine. Be that way!'
                return x
            continue
        else:
            x= 'Fine. Be that way!'
            return  x
            


    x= 'Whatever.'
    return  x

        
            
        
