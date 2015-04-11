class Hamming

  def self.compute(dna1, dna2)
    hamming_distance = 0
    current_index = 0

    while current_index < final_index(dna1, dna2) do
      if array(dna1)[current_index] != array(dna2)[current_index]
        hamming_distance += 1
      end
      current_index += 1
    end

    hamming_distance
  end

  private

    def self.array(dna)
      dna.split("")
    end

    def self.final_index(dna1, dna2)
      if dna1.split("").count == dna2.split("").count
        final_index = (dna1.split("").count)
      else
        final_index = 0
      end
    end

end
 
