class Series

  attr_reader :digits

  def initialize digits
    @digits = digits.chars.map &:to_i
  end

  def slices number
    raise ArgumentError if number > digits.size
    
    digits.each_cons(number).to_a
  end

end
