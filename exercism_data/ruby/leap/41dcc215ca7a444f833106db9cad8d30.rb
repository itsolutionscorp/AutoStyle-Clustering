class Year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by_4? && (divisible_by_400? || !divisible_by_100?)
  end

  %w(4 100 400).each do |count|
    define_method("divisible_by_#{count}?") { @year % count.to_i == 0 }
  end

end
