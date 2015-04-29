require 'delegate.rb'
class Year < SimpleDelegator
    
  def leap?
    divisible_by?(4) && (!divisible_by?(100) || divisible_by?(400))
  end
    
  def divisible_by?(denominator)
	  remainder(denominator) == 0
  end
  
end
