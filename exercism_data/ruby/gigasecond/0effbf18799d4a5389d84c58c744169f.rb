#!/usr/bin/ruby

class Gigasecond

def Gigasecond.from(a)

#step 1 I can read in argvs
#v1 = ARGV[0]

bday = ARGV[0]

birthDate = Time.mktime(bday)

gigaDate = birthDate + (10**9)

##debug section ##
#puts birthDate
#puts gigaDate

return gigaDate
end
end
