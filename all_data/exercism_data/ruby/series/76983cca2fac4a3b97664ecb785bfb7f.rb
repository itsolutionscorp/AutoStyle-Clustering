class Series
  def initialize(string)
    @number = string.chars.map(&:to_i)
  end

  def slices(n)
    raise ArgumentError if n > @number.length

    @number.each_index.map do |index|
      last_index = index + n-1
      @number[index..last_index] if last_index < @number.length
    end.compact
  end
end
