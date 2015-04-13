def compute(x, y)


    first_string = Array.new
    first_string = x.chars.to_a

    second_string = Array.new
    second_string = y.chars.to_a



    strings_equal = first_string.length == second_string.length

    difference = (first_string.length - second_string.length).abs

    first_end_element = (first_string.length - difference).abs
    second_end_element = (second_string.length - difference).abs

    first_string = first_string.slice(0, first_end_element)
    second_string = second_string.slice(0, second_end_element)



    @counter = 0
    @hamming_distance = 0

    first_string.each do |f|
      if second_string[@counter] != f
        @hamming_distance += 1
      end
      @counter += 1
    end


    return @hamming_distance
  end