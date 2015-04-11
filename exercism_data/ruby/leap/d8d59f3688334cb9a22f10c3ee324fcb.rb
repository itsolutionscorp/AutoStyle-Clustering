class Year
  attr_accessor :year

  def initialize(year=1900)
    @year = year
  end

  def leap?
    @divisors = {400=>true, 100=>false, 4=>true}
    @divisors.each do |divisor,condition|
      leap_check = divisible_by?(year)
      return condition if leap_check.call(divisor)
    end
    return false
  end
  def divisible_by?(dividend)
    Proc.new { |divisor| dividend % divisor == 0 }
  end
end
