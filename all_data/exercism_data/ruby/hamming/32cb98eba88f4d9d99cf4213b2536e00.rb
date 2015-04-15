class Hamming
  def self.compute(dna1, dna2)
    #compute Hamming distance of 2 DNA strings        
    prep1 = parse dna1
    prep2 = parse dna2

    distance prep1, prep2
  end

  def self.parse(dna_string)
    #parse string with DNA codes into a hash to describe the DNA string as 
    #list of pairs - position and amino acid code
    dna_hash = Hash.new
    dna_string.split(//).each_with_index do |position, amin_acid|
      dna_hash[amin_acid] = position
    end
  
    dna_hash
  end

  def self.distance(dna_hash1, dna_hash2)
    #if amin_acid codes for the same position are not the same, increase distance
    distance = 0
    dna_hash1.each do |position, amin_acid|
      unless dna_hash2[position] == amin_acid then distance+=1 end
    end
  
    distance
  end
end
