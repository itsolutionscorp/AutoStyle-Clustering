class Year
  
    #need to declare an instance of Year with the test number i.e. 1996 
   def self.leap?(year)
     self.new(year).leap?
   end
   
   def initialize(year)
     @year = year
   end
  
  
  def leap?
    divisible_by?(4) and (!divisible_by?(100) or divisible_by?(400))
    # if divisible_by?(400) then true
    # elsif divisible_by?(100)then false
    # elsif divisible_by?(4)then true
    # else false 
    # end 
  end
  
  private
  
  def divisible_by?(n)
    @year % n == 0
  end
    
end
