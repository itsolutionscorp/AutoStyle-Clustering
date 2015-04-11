class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    result = 0
    power = 1

    @binary.reverse.each_char do |num|
      result += num.to_i * power
      power *= 2
    end
    result
  end
end
