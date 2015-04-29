def compute( first_string, second_string )
    index = 0
    counter = 0
    first_string.each_char do |char|
      if second_string[index] != nil
        counter += 1 unless char == second_string[index]
      end
      index += 1
    end
    return counter
  end