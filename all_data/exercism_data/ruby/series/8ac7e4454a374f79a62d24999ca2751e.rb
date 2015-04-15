class Series
  def initialize(input)
    @string = input
  end

  def slices(n)
    raise ArgumentError if n > string.length
    result = Array.new
    while not(string.empty?)
      result << string.slice(0...n).split(//).map(&:to_i)
      string.slice!(0)
    end
    while result.last.length < n
      result.pop
    end
    result
  end

  private
  attr_reader :string
end
