class Integer < Numeric
  def to_roman
    convert(self)
  end

  def convert(number)
    current_literal = ""
    if number > 0
      remainder = number - get_denomenator(number)
      current_literal += "#{look_up(number)}#{convert(remainder)}"
    end
    current_literal
  end

  def get_denomenator(number)
    case number
    when 1..3
      1
    when 4
      4
    when 5..8
      5
    when 9
      9
    when 10..39
      10
    when 40..49
      40
    when 50..89
      50
    when 90..99
      90
    when 100..399
      100
    when 400..499
      400
    when 500..899
      500
    when 900..999
      900
    else
      1000
    end
  end

  def look_up(number)
    case number
    when 1..3
      "I"
    when 4
      "IV"
    when 5..8
      "V"
    when 9
      "IX"
    when 10..39
      "X"
    when 40..49
      "XL"
    when 50..89
      "L"
    when 90..99
      "XC"
    when 100..399
      "C"
    when 400..499
      "CD"
    when 500..899
      "D"
    when 900..999
      "CM"
    else
      "M"
    end
  end
end
