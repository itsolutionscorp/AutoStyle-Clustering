def compute(original,mutated)
    distance = 0
    original.each_char.with_index do |ele, index|
      break if index == mutated.length
      if ele != mutated[index]
        distance += 1
      end
    end
    distance
  end