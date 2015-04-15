class Series
  def initialize(num)
    @number = num
  end

  def slices(s)
    fail ArgumentError if s > @number.length

    @number.scan(/(?=(\d{#{s}}))/).map { |x| x[0].each_char.map(&:to_i) }
  end
end
