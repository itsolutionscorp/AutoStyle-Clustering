class Series
  def initialize number
    @number = number.chars.map &:to_i
  end

  def slices number
    raise ArgumentError if @number.count < number
    @number.each_cons(number).to_a
  end
end
