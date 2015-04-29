class Series
  def initialize digits
    @digits = digits
  end

  def slices length
    if length > @digits.length
      raise ArgumentError
    end

    @digits.each_char.map(&:to_i).each_cons(length).to_a
  end
end
