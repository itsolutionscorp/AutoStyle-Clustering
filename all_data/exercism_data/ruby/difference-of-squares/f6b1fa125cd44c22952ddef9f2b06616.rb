#! /usr/bin/ruby

# sum of squares problem

oneTa = (1..100).to_a

def regSum 
oneTa = (1..100).to_a
num = 0
	
	oneTa.each { |x| num +=x}
	
return num
end


def sqSum
oneTa = (1..100).to_a
num = 0

oneTa.each { |x| num +=x**2}

return num
end

def diffSum
oneTa = (1..100).to_a
a = sqSum
b = regSum
x = a-b
puts x
return x
end


diffSum
