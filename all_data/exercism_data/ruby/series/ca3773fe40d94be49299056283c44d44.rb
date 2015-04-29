class Series
  attr_reader :numbers

  def initialize(string)
    @numbers = collect_integers_from(string)
  end

  def slices(scope)
    raise ArgumentError if scope > numbers.length

    result = []
    numbers.length.times do |i|
      slice = numbers[i,scope]
      result << slice if slice.length == scope
    end
    result
  end

  private

  def collect_integers_from(string)
    string.chars.map{|c| c.to_i}
  end

end
