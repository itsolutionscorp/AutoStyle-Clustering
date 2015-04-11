module Complement

  def self.of_dna(dna)
    self.create_complement(dna, "dna")
  end

  def self.of_rna(rna)
    self.create_complement(rna, "rna")
  end

  def self.create_complement(strand, strand_type)
    complement = ''

    strand.chars.each do |acid|
      if acid == 'G'
        complement += 'C'
      elsif acid == 'C'
        complement += 'G'
      elsif acid == 'A'
        complement += 'T' if strand_type == "rna"
        complement += 'U' if strand_type == "dna"
      else
        complement += 'A'
      end
    end
    complement
  end
end
