class Integer

  TRANSLATION = {
    1 => "I",
    5 => "V",
    10 => "X",
    50 => "L",
    100 => "C",
    500 => "D",
    1000 => "M"
  }

  def to_roman
    [1000, 100, 10, 1].map do |current|
      calculate_subpart(current, self % (current * 10))
    end.join
  end

  private

  def calculate_subpart(unit, num)
    case num / unit
    when 1..3
      return TRANSLATION[unit] * (num / unit)
    when 4
      return TRANSLATION[unit] + TRANSLATION[unit * 5]
    when 5..8
      return TRANSLATION[unit * 5] + TRANSLATION[unit] * (num / unit - 5)
    when 9
      return TRANSLATION[unit] + TRANSLATION[unit * 10]
    end
  end

end
