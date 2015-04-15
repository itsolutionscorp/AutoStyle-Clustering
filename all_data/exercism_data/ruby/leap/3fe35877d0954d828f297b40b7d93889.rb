class Year

  def initialize(value)
    @value = value
  end

  def value
    @value
  end

  def divisible?(n)
    return self.value % n == 0
  end

  def self.leap?(v)
    y = Year.new(v)
    return true if y.divisible?(400)
    return false if y.divisible?(100)
    return y.divisible?(4)
  end

end
