class Trinary
  def initialize(str)
    if is_valid?(str)
      @value = parse(str)
    else
      @value = 0
    end
  end

  def is_valid?(str)
    !str.match(/[^012]/)
  end

  def parse(str)
    value = 0
    str.reverse.each_char.with_index do |digit, index|
      value += digit.to_i * (3 ** index)
    end
    value
  end

  def to_decimal
    @value
  end
end
