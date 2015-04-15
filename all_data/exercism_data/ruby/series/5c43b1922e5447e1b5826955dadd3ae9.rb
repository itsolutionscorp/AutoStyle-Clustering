class Series

  def initialize(numbers)
    @numbers = numbers
    @series = []
  end

  def slices(n)
    raise ArgumentError if n > @numbers.size

    @numbers.split("").each_index do |i|
      break if @numbers.size < n + i
      @series << @numbers.slice(i, n).split("").map(&:to_i)
    end
    @series
  end

end
