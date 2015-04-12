class Hamming
  def compute(original, mutation)
    i = 0
    distance = 0
    while i < original.length
      distance += 1 if original[i] != mutation[i]
      i += 1
    end

    distance
  end
end
