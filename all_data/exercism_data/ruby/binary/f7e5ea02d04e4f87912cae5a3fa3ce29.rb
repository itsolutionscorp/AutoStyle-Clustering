class Binary
  attr_accessor :binary

  def initialize binary
    self.binary = binary
  end

  def to_decimal
    return 0 unless valid?
    max_i = digits.length - 1
    digits.each_with_index.reduce(0) do |base10,(digit,i)|
      base10 + ( get_value(digit) * 2**(i - max_i).abs )
    end
  end

  def digits
    binary.chars
  end

  def valid?
    binary =~ /^[10]+$/
  end

  private
    def get_value str_digit
      str_digit.ord - '0'.ord
    end
end
