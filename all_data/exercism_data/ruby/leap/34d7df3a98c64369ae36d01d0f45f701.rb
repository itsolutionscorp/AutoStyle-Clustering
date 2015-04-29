class Year

  attr_accessor :the_year
  
  class << self
    def leap? year
      @the_year = year
      
      regular_leap_year?
    end
    
    def regular_leap_year?
      if (divides_by_4? && !divides_by_100?) || divides_by_400?
        true
      end
    end
    
    def divides_by_4?
      @the_year % 4 == 0
    end
    
    def divides_by_100?
      @the_year % 100 == 0
    end
    
    def divides_by_400?
      @the_year % 400 == 0
    end
  end
  
end
