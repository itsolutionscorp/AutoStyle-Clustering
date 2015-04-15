class Series
  def initialize number
    @array_number = number.chars.map(&:to_i)
  end

  def slices number
    raise ArgumentError.new 'eee' if @array_number.count < number
    @array_number.each_cons(number).to_a
  end
end
