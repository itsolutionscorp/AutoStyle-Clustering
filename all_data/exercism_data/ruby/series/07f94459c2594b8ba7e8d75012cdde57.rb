class Series
  def initialize(input)
    @numbers = input.split('').map(&:to_i)
  end

  def slices(num)
    fail ArgumentError if num > @numbers.size
    @numbers.each_cons(num).to_a
  end
end
