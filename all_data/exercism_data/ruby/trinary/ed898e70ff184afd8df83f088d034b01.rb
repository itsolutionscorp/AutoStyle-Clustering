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
    str.reverse.each_char.with_index.map do |digit, index| 
      digit.to_i * (3 ** index)
    end.reduce(:+)
  end

  def to_decimal
    @value
  end
end
