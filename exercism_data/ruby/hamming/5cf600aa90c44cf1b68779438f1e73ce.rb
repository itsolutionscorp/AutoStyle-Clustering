class Hamming

  def self.compute(dna1, dna2)
    hamming(dna1,dna2)
  end

  private

    def self.hamming(dna1, dna2)
      hamming_distance = 0
      array(dna1).each_index do |index| 
        if  compare(dna1, dna2, index)
          hamming_distance += 1
        end
      end
      hamming_distance
    end

    def self.array(dna)
      dna.split("")
    end

    def self.compare(dna1, dna2, index)
      if !array(dna1)[index].nil? && !array(dna2)[index].nil?
        array(dna1)[index] != array(dna2)[index]
      end
    end
    
end
 
