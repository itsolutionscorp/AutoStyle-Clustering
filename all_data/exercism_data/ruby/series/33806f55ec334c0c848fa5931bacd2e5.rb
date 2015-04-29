class Series
  def initialize(digits)
    @digits = digits.chars.map(&:to_i)
  end

  def slices(int)
    raise ArgumentError if int > @digits.size
    slices = []
    @digits.each_with_index do |d, i|
      break slices if @digits[i, int].size < int
      slices << @digits[i, int] 
    end
    slices
  end

end
