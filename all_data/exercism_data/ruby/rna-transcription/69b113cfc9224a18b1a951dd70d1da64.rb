class Complement

  DNA_COMPLEMENTS = {
    "G" => "C", 
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert


  def self.find_complements(type, strand)
    strand = strand.scan(/\w/)
    complemented_strand = ""

    strand.each do |c|
      if type == :rna
        complemented_strand << RNA_COMPLEMENTS[c]
      else
        complemented_strand << DNA_COMPLEMENTS[c]
      end
    end

    complemented_strand
  end

  def self.of_dna(strand)
    find_complements(:dna, strand)
  end

  def self.of_rna(strand)
    find_complements(:rna, strand)
  end
end
