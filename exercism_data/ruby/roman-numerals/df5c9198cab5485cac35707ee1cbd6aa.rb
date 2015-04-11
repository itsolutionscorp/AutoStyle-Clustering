class Fixnum
  ROMAN_DIGITS = { 1 => { primary: 'I', middle: 'V', next: 'X' },
                   10 => { primary: 'X', middle: 'L', next: 'C' },
                   100 => { primary: 'C', middle: 'D', next: 'M'},
                   1000 => { primary: 'M', middle: nil, next: nil } }

  def to_roman
    4.times.reverse_each.each_with_object('') do |place_value, roman_number|
      roman_number << process(10**place_value).to_s
    end
  end

  def process(place_value)
    digit = (self / place_value) % 10
    symbol = ROMAN_DIGITS[place_value]

    case digit
    when (1..3)
      symbol[:primary] * digit
    when 4
      symbol[:primary] + symbol[:middle]
    when 5
      symbol[:middle]
    when (6..8)
      symbol[:middle] + symbol[:primary] * (digit - 5)
    when 9
      symbol[:primary] + symbol[:next]
    end
  end
end
