class Raindrops
  def self.convert(num)
  	result=""
  	if num % 3 ==0 || num % 5==0 || num % 7==0
  	  result += "Pling" if num % 3 == 0
  	  result += "Plang" if num % 5  == 0
  	  result += "Plong" if num % 7 ==0
  	else
  		return "#{num}"
    end
    return "#{result}"
  end
end
