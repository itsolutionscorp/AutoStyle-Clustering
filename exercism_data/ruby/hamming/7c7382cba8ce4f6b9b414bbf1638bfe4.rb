class Hamming
  def self.compute(first_input, second_input)
    distance = 0
    
    if first_input != second_input
      first_input.chars.each_with_index do |first_input_char, index|
        break if index == second_input.length
        distance += 1  if first_input_char != second_input.chars[index]
      end
    end

    distance
  end
end
