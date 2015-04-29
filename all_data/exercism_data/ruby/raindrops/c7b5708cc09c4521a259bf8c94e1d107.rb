class Raindrops 
  class << self 
    def convert(number)
	    condition_3 = (number % 3 == 0)
	    condition_5 = (number % 5 == 0)
	    condition_7 = (number % 7 == 0)

            #for the next iteration, I want to use something other than if else statements

	    if condition_3 && condition_5 && condition_7 
		    "PlingPlangPlong" 
	    elsif condition_3 && condition_5 
		    "PlingPlang"
	    elsif condition_3 && condition_7 
		    "PlingPlong" 
	    elsif condition_5 && condition_7 
		    "PlangPlong"
	    elsif condition_7 
		    "Plong" 
	    elsif condition_5 
		    "Plang" 
	    elsif condition_3 
		    "Pling" 
	    else
		    number.to_s 
	    end
    end    
  end
end
