def compute(original, mutation)
    score = 0
    (0..[original.length, mutation.length].min - 1).each do |index|
      if original[index] != mutation[index]
        score+=1
      end
    end
    score
  end