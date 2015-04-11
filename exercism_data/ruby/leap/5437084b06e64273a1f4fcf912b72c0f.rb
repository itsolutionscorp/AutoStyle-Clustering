class Year

  def self.leap?(year)
    YearTest.new(year).leap?
  end

  YearTest = Struct.new(:year) do
    def leap?
      case
        when year_divides_into?(400) then true
        when year_divides_into?(100) then false
        when year_divides_into?(4) then true
        else false
      end
    end

    def year_divides_into? divisor
      (year % divisor).zero?
    end
  end

end
