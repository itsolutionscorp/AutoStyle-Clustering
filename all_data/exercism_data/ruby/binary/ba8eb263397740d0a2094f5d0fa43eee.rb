class Binary
  def initialize(number)
    @number = number
  end

  def to_decimal
    return 0 unless @number =~ /\A(0|1)+\z/
    decimal = 0
    exponent = 0
    @number.reverse.each_char do |char|
      decimal += (char.to_i * (2**exponent))
      exponent += 1
    end
    decimal
  end
end
