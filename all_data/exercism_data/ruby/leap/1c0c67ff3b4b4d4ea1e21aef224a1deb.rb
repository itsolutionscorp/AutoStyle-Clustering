class Year
  attr_accessor :year

  def initialize(year=1900)
    @year = year
  end

  def leap?
    @divisors = {400=>true, 100=>false, 4=>true}
    @divisors.each do |divisor,condition|
      leap_check = divisible_by?(year)
      flag = leap_check.call(divisor)
      return condition if flag
    end
    return false
  end
  def divisible_by?(dividend)
    Proc.new { |divisor| dividend % divisor == 0 }
  end
end
