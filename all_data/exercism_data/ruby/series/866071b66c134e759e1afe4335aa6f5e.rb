class Series
  def initialize(num)
    @series = num
  end

  def slices(digits)
    s_leng = @series.length
    raise ArgumentError if s_leng < digits

    0.upto(s_leng - digits).map { |current|
      @series.slice(current..(current + digits - 1)).chars.map { |val| val.to_i }
    }
  end
end
