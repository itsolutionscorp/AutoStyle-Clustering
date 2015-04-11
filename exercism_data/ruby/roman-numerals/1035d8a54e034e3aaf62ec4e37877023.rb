require "benchmark"

class Fixnum 

	def to_roman
	  RomanBuilder(self)
	end

def RomanBuilder(num)
  raise ArgumentError.new("The Romans weren't counting that high...") if num >= 10000

  num_array = num.to_s.split("")
  j = num_array.size - 1
  num_hash = Hash.new
  num_array.each do |i|
  	num_hash[j] = i.to_i
  	j -= 1
  end

  finalString = ""
  num_hash.each do |key, value|
  	finalString += RomanFramework(value, key)
  end
  return finalString
end


def RomanFramework(num,tier)
	romanString = ""
	case tier

	  when 0
	  	a,b,c = "I","V","X"
	  when 1
	  	a,b,c =  "X","L","C"
	  when 2
	  	a,b,c = "C", "D", "M"
	  when 3
	  	a, b, c = "M", "MMMMM", "MMMMMMMMMM"

	end

	  case num
	  when 0
	  	return romanString
	  when 1..3
	  	num.times do 
	  		romanString += a
	  	end
	  	return romanString
	  when 4
	  	romanString = a+b
	  	return romanString
	  when 5..8
	  	romanString = b + RomanFramework(num % 5,tier)
	  when 9
	  	romanString = a + c
	  	return romanString
	  end

	end
  
end
