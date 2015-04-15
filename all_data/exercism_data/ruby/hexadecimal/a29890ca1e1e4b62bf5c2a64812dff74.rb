class Hexadecimal
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def to_decimal
    return 0 if number =~ /[^a-f0-9]/
    number.chars.reverse.map.with_index do |digit, index|
      convert(digit) * 16**index
    end.reduce(0, :+)
  end

  def convert(letter)
    letter =~ /[a-f]/ ? letter.tr("abcdef", "012345").to_i + 10 : letter.to_i
  end
end
