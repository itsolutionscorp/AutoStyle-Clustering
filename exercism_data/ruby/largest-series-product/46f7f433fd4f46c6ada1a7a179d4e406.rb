class Series

  def initialize(data)
    @data = data
  end

  def digits
    @data
      .chars
      .map { |c| c.to_i }
  end

  def slices size
    return [] if size == 0
    @data
      .chars
      .map(&:to_i)
      .each_cons(size)
      .map { |slice| slice }
  end

  def largest_product size
    raise ArgumentError if size > @data.size
    slices(size)
      .map { |slice| slice.inject(:*) }
      .max || 1
  end

end
