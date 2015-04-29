class Year
  def initialize()
		
  end	
  # This is a static method	
  def Year.leap?(year) 
		isLeap=false # By default the flag is false
		if (year %4==0 && (year%100!=0 || year %400==0)) # If the year parameter is a leap year, we change the flag to true.
			isLeap=true		
		end	
		return isLeap # The final result 		
  end	
end
