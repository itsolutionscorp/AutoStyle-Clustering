class Year
  attr_reader :input_year

  def initialize(input_year)
    @input_year = input_year
  end

  def leap?
    if input_year % 400 == 0
      return true
    elsif input_year % 100 == 0
      return false
    elsif input_year % 4 == 0
      return true
    end
  end

end
