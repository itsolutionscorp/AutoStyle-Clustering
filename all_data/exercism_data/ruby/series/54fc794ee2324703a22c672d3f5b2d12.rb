class Series
  def initialize(digits)
    @digits = digits.split(//).map { |e| e.to_i }
  end
  def slices(length)
    raise ArgumentError if length > @digits.size
    index  = length - 1
    slices = []
    (@digits.size - index).times do |n|
      slices << @digits[n..(n + index)]
    end
    slices
  end
end
