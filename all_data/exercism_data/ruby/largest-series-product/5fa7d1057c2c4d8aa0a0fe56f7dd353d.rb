class Series
  def initialize(numberAsString)
    @numberAsString = numberAsString
  end

  def digits
    @digits ||= @numberAsString.split('').map { |i| i.to_i }
  end

  def slices(lengthOfSlice)
    digits.each_cons(lengthOfSlice).to_a
  rescue ArgumentError
    []
  end

  def largest_product(lengthOfSlice)
    raise ArgumentError if lengthOfSlice > digits.length
    largest_factors(lengthOfSlice).reduce(&:*)
  end

  private

  def largest_factors(lengthOfSlice)
    slices(lengthOfSlice).sort do |slice1, slice2|
      slice2.reduce(&:*) <=> slice1.reduce(&:*)
    end.first || [1]
  end
end
