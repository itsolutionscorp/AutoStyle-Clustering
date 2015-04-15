class Series
  def initialize(string)
   @string = string 
  end

  def slices(digits)
    raise ArgumentError if digits > @string.size 

    positions = (0..@string.size - digits)
    positions.each_with_object([]){|num, results| results << slice(num, digits)}
  end

  private

  def slice(position, digits)
    @string.chars.map(&:to_i).rotate(position).take(digits)
  end

end
