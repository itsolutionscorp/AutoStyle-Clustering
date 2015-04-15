class Series
  def initialize(numbers_string)
    @numbers_array = []
    @numbers_array = numbers_string.chars.map { |char| char.to_i }
  end

  def slices(size)
    result_array = []
    @numbers_array.each_with_index do |number, index|
      number_slice = @numbers_array[index..index+size-1]
      if number_slice.length == size
        result_array << number_slice 
      else
        break
      end
    end
    if result_array == []
      raise ArgumentError
    else
      return result_array
    end
  end

end
