class Binary
  def initialize string
    @binary = valid_binary(string)
  end

  def to_decimal
    @binary.map.with_index { |number, index| number.to_i * 2**index }.inject(:+)
  end

  def valid_binary string
    string.scan(/[2-9\D]/).empty? ? string.reverse.split("") : [0]
  end
end
