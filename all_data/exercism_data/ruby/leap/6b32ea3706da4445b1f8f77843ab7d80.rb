class Year

  def initialize( input_year )
    @year = input_year.to_i
    @leap_year = false
    set_leap_year
  end

  def leap?
    @leap_year 
  end

  def set_leap_year
    if    (@year % 400).zero?
      @leap_year = true 
    elsif (@year % 4).zero? && !(@year % 100).zero?
      @leap_year = true
    end
  end


end
