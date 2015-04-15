class Trinary
  @@decimal_map = Hash.new do |hash, key|
    hash[key] = key.chars.reverse.each_with_index.reduce(0) do |decimal, (char, index)|
      decimal + char.to_i * 3**index
    end
  end

  attr_reader :trinary

  def initialize(str)
    @trinary = str
  end

  def to_decimal
    @@decimal_map[trinary]
  end
end
