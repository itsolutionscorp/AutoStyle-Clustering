class Series
  def initialize(string)
    @string = string
  end
  
  def digits
    @string.split("").map { |n| n.to_i }
  end
  
  def slices(count)
    raise ArgumentError if count > @string.length
    digits.each_cons(count).to_a
  end  
  
  def largest_product(count)
    return 1 if count == 0
    slices(count).map { |n| n.reduce(:*) }.max
  end
  
end
