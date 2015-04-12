class Hamming
  def compute(first_input, second_input)
    distance = 0
    
    if first_input != second_input
      first_input_array = first_input.split("")
      second_input_array = second_input.split("")

      first_input_array.each_with_index do |first_input_char, index|
        if index == second_input.length
          break
        else
          if first_input_char != second_input_array[index]
            distance += 1
          end
        end
      end
    end

    distance
  end
end
