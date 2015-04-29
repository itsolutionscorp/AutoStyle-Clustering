def compute(first, second)
    i = 0
    hamming_score = 0
    first.length > second.length ? fewest = second.length: fewest = first.length
    fewest.times do
      first_array = first.split ""
      second_array = second.split ""
      first_array[i] == second_array[i] ? hamming_score : hamming_score += 1
      i+=1 
    end
    return hamming_score