class Series
  attr_reader :digits
  def initialize string
    @digits = string.scan(/[0-9]/).map(&:to_i)
  end
  def slices length
    raise ArgumentError if length > digits.length
    digits.each_index.with_object([]) do |i,list|
      list << digits[i...i+length] if digits[i...i+length].length == length
    end
  end
end
