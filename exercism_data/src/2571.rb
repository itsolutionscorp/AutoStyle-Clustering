def compute(input, output)
    distance = 0
    index = 0
    input.each_char do |char|
      output_char = output[index]

      unless output_char == char
        distance += 1
      end
      index += 1
    end

    distance
  end