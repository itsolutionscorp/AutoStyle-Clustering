class Hamming
  def self.compute(first_input, second_input)
    distance = 0
    
    if first_input != second_input
      first_input.chars.each_with_index do |first_input_char, index|
        if index == second_input.length
          break
        else
          if first_input_char != second_input.chars[index]
            distance += 1
          end
        end
      end
    end

    distance
  end
end
