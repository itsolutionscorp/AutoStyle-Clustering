class Binary
  attr_accessor :binary

  def initialize binary
    self.binary = binary
  end

  def to_decimal
    return 0 unless valid?
    digits.reverse.each_with_index.reduce(0) do |base10,(digit,i)|
      base10 += digit.to_i * (2**i)
    end
  end

  def digits
    binary.chars
  end

  def valid?
    binary =~ /^[10]+$/
  end
end
