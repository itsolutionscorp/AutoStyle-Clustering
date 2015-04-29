class Year
  
  class << self
    def leap? year      
      leap_year? year
    end
    
    def leap_year? year
      if (divides_by?(year, 4) && !divides_by?(year, 100)) || divides_by?(year, 400)
        true
      end
    end
    
    def divides_by?(year, number)
      year % number == 0
    end
  end
  
end
