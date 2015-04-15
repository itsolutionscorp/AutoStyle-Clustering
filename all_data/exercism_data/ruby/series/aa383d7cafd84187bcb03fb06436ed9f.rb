class Series
  def initialize(digits)
    @digits = digits
  end

  def slices(size)
    raise ArgumentError, "Slice size is too big." if size > @digits.length
    slices = []
    last = @digits.length - size
    (0..last).each do |start|
      slices << slice(start, size)
    end
    slices
  end

  private

  def slice(start, size)
    slice = @digits[start, size]
    slice.split("").map(&:to_i)
  end
end
