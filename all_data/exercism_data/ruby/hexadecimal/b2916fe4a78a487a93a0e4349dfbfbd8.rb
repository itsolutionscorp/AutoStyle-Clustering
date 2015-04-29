class Hexadecimal
  def initialize(hex_number)
    @sign, @hex_number = parse(hex_number)
  end

  def to_decimal
    decimals = digits.reverse.map.with_index { |hex, pos| hex * 16**pos }
    decimal = decimals.inject(0, :+)

    positive? ? decimal : 0 - decimal
  end

  private

  def positive?
    @sign == '+'
  end

  def negative?
    !positive?
  end

  def parse(hex_number)
    /^(?<sign>[+-]?)\s*(?<digits>[\da-fA-F]+)$/ =~ hex_number
    sign = '+' unless sign == '+'
    [sign, digits || '0']
  end

  def digits
    @hex_number.chars.map { |h| to_digit(h) }
  end

  def to_digit(hex)
    case hex
    when /\d/ then hex.to_i
    when /a/i then 10
    when /b/i then 11
    when /c/i then 12
    when /d/i then 13
    when /e/i then 14
    when /f/i then 15
    else fail ArgumentError,
              "ArgumentError: not a hexadecimal (#{hex} for 0..9, a-f, A-F)"
    end
  end
end
