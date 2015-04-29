class Hamming

  def self.compute(original_dna, mutated_dna)
    hamming_distance = 0
    step = 1
    original_dna.each_char do |c|

      unless c == mutated_dna[step - 1]
        hamming_distance = hamming_distance + 1
      end
      step = step + 1
      
      if step > original_dna.length or step > mutated_dna.length
        return hamming_distance
      end

    end
    return hamming_distance
  end
end
