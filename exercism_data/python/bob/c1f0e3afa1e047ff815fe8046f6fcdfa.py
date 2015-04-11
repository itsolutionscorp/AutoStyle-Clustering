class Bob:
    #
    #  ?            Sure
    #  all caps     whoa
    #  other      'Whatever.'  
    v = 'what?'
    def hey(self,v):
        #
        if v == None :
            return 'Whatever.'
        if len(v) > 0:
            if v[-1:] == '?':
                if v == v.upper():
                    return "Woah, chill out!"
                else:
                    return "Sure." 
                # 
                return 'Sure.'
        if v == v.upper():
            #
            return 'Woah, chill out!' 
        if v.strip() == '': # '    ':
            #
            return 'Fine. Be that way!'
        return 'Whatever.'
       
a = Bob()
a.hey(' ')
 
