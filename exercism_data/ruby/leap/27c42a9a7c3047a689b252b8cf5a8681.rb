class Year

	attr_reader :year

	def initialize(number)
	   @year = number.to_i
	end

	def leap?
		div_by_four && (!div_by_100 || div_by_400)
	end

	private 

	def div_by_four
	    (year % 4) == 0
	end

    def div_by_100
       (year % 100) == 0
    end

    def div_by_400
    	(year % 100) == 0
    end

end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
