class Hamming

  def self.compute(base, mutated)
    hamming_difference = 0
    length = min_length(base, mutated) - 1

    0.upto(length) do |index|
      hamming_difference += 1 if base[index] != mutated[index]
    end

    hamming_difference
  end

  def self.min_length(base, mutated)
    if base.length > mutated.length
      mutated.length
    else
      base.length
    end
  end

end
