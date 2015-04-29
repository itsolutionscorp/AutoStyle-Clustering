class Series
  def initialize(digits_str)
    @digits = digits_str
  end

  def slices(size)
    if size > @digits.length
      raise ArgumentError, "Your slice is too big"
    end

    slices = []
    (@digits.length - size + 1).times do |i|
      slices << @digits.slice(0+i, size).split('').map{|x| x.to_i}
    end
    slices
  end
end
