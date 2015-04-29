class Binary
  attr_reader :binary

  @@decimal_map = Hash.new do |hash, key|
    hash[key] = key.reverse.chars.each_with_index.reduce(0) do |decimal, (digit, index)|
      decimal + (digit.to_i * (2**index))
    end
  end

  def initialize(binary)
    @binary = binary
  end

  def valid
    /^[01]*$/ =~ binary
  end

  def to_decimal
    valid ? @@decimal_map[binary] : 0
  end
end
