class Series
  def initialize(string)
    @string = string.chars.map(&:to_i)
  end

  def slices(digits)
    raise ArgumentError if digits > @string.size 
    
    @string.each_cons(digits).to_a
  end
end
