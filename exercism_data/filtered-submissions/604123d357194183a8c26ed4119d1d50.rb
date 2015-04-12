def compute(original, mutant)
    result = 0
    if original.length > mutant.length
      original = original[0, mutant.length]
    end
    original.split("").each_with_index do |letter, index|
        result+=1 if letter!=mutant[index]
    end
        return result
  end