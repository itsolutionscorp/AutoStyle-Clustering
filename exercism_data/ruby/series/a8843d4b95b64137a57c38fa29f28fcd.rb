class Series
  def initialize(chain)
    @digits = chain.chars.map(&:to_i)
  end

  def slices(limit)
    fail ArgumentError if limit > @digits.length
    start = 0
    series = []
    until (limit + start) == (@digits.length + 1)
      series <<  @digits.slice(start...(limit + start))
      start += 1
    end
    series
  end
end
