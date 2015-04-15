class Series
  def initialize(number_string)
    @number_array = number_string.chars.map {|x| x.to_i}
  end

  def slices(parts)
    unless @number_array.length < parts
      0.upto(num_of_slices(parts)).map {|x| @number_array[x..(x + parts - 1)]}
    else raise ArgumentError
    end
  end

  def num_of_slices(parts)
    @number_array.length - parts
  end
end
