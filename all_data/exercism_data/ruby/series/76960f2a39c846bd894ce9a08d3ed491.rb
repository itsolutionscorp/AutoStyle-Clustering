class Series
  def initialize(digits)
    @digits = digits.split(//).map { |d| d.to_i }
  end

  def slices(length)
    raise ArgumentError if length > @digits.length
    0.upto(@digits.length - length).each_with_object([]) do |index, array|
      array << @digits[index, length]
    end
  end
end
