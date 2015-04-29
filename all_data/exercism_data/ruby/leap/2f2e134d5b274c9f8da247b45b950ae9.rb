class Year

  def initialize(year)
    @year = year
  end

  [4, 100, 400].each do |num|
    define_method("divisible_by_#{num}?") { @year % num == 0 }
  end

  def leap?
    # This is the efficient version using some bitwise operators
    # ((@year & 3) == 0 && ((@year % 25) != 0 || (@year & 15) == 0)) ? true : false
    case
    when divisible_by_400? then true
    when divisible_by_100? then false
    when divisible_by_4? then true
    else false
    end
  end

end
