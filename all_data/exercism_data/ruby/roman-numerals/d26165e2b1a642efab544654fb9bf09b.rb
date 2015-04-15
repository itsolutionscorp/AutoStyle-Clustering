class Fixnum 

	def to_roman
	  RomanBuilder(self)
	end

def RomanBuilder(num)
  raise ArgumentError.new("The Romans weren't counting that high...") if num >= 10000
  hash = Hash.new
  finalString = ""

  num.to_s.each_char.to_a.reverse.map.with_index { |w, i| hash[i]=w.to_i}
  while(!hash.empty?)
  	finalString += RomanFramework(hash.values.last,hash.keys.last)
  	hash.delete(hash.keys.last)
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
