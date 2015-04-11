class Year
  attr_accessor :year

  class << self
    def is_leap_year?(year)
      Year.new(year).is_leap_year?
    end
    alias :leap? :is_leap_year?
  end

  def is_leap_year?
    case
    when year_divides_into?(400) then true
    when year_divides_into?(100) then false
    when year_divides_into?(4) then true
    else false
    end
  end

  protected

  def initialize(year)
    @year = year
  end

  def year_divides_into? divisor
    (year % divisor).zero?
  end
end
