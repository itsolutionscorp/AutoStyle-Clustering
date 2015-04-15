class Integer
  def divisible_by?(number)
    self.to_f / number == self / number
  end
end

class Year
  attr_reader :value

  def initialize(value)
    @value = value
  end

  def leap?
    # http://en.wikipedia.org/wiki/Leap_year#Algorithm
    return true if @value.divisible_by? 400
    return false if @value.divisible_by? 100
    return true if @value.divisible_by? 4
    false
  end
end
