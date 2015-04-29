class Series
  def initialize(num)
    @series = num
  end

  def slices(digits)
    s_leng = @series.length - digits
    raise ArgumentError if s_leng < 0

    0.upto(s_leng).map { |c|
      @series.slice(c..(c + digits - 1)).chars.map(&:to_i)
    }
  end
end
