  class Year
    
    def initialize(year_to_test)
      @year_to_test = year_to_test 
    end

    def leap?
     (divides_evenly_by?(4) && (divides_evenly_by?(400) || doesnt_divide_evenly_by?(100)))
    end

    def divides_evenly_by?(number)
      @year_to_test % number == 0
    end

    def doesnt_divide_evenly_by?(number)
      @year_to_test % number != 0
    end

  end
