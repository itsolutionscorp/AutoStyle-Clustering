class Series
  attr_reader :digits
  def initialize string
    @digits = string.scan(/[0-9]/).map(&:to_i)
  end
  def slices length
    raise ArgumentError if length > digits.length
    digits.each_index.with_object([]) do |i,list|
      list << digits[i...i+length] if digits.length >= i+length
    end
  end
  def largest_product length
    length.zero? ? 1 : products(length).sort.last
  end
  private
  def products length
    slices(length).map{|slice| slice.reduce(:*)}
  end
end
