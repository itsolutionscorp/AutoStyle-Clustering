class Series
  def initialize(source)
    @source = source
  end

  def slices(size)
    count = @source.length - size
    raise ArgumentError if count < 0
    slices = (0..count).map{ |offset| @source[offset, size] }
    slices.map{ |slice| slice.chars.map(&:to_i) }
  end
end
