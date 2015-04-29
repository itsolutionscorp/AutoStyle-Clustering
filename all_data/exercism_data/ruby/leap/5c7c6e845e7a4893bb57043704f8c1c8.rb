class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by_400? || !divisible_by_100? && divisible_by_4?
  end

  private

  [4,100,400].each do |multiple|
    define_method("divisible_by_#{multiple}?") do
      @year % multiple == 0
    end
  end
end
