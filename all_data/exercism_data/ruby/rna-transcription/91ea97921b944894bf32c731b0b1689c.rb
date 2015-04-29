class Complement
  # nucleotides complement map
  NCM = {"G" => "C", "C" => "G", "T" => "A", "A" => "U" }

  class << self
    def of_dna(nucleotides)
      run(nucleotides) do |n|
        NCM[n.to_s.upcase]
      end
    end
    def of_rna(nucleotides)
      run(nucleotides) do |n|
        NCM.invert[n.to_s.upcase]
      end
    end
    def run(nucleotides)
      return "invalid" if nucleotides.class != String
      nucleotides.chars.map{ |n| yield n }.join('')
    end

  end


end
