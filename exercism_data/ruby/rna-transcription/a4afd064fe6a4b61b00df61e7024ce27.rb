#!/usr/bin/ruby

#class Gigasecond

#def Gigasecond.from(a)

#step 1 I can read in argvs
#v1 = ARGV[0]

DNA = ARGV[0]

#puts DNA

def doCalc
puts "Here is the new RNA"
rna = []

eric_holder = "a"

DNA.each_char do |c|
if c == "G" 
	eric_holder = "C"
elsif c =="C"
	eric_holder = "G"
elsif c == "T"
	eric_holder = "A"
elsif c == "A"
	eric_holder = "U"
else eric_holder = c

rna << eric_holder
end

end


doCalc

puts rna

return rna
end

##"mêlée".each_char do|c| puts c end   ## template
