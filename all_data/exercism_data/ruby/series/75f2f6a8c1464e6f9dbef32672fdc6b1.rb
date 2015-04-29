class Series
  def initialize(numbers)
    @numbers = numbers.split('').map {|i| i.to_i}
  end
  def slices(num)
    raise ArgumentError if num > @numbers.length
    @numbers.each_cons(num).to_a
  end
end
