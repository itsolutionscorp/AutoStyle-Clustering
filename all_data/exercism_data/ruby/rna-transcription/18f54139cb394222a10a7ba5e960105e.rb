class Complement

  @dna_complement_pairs = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  @rna_complement_pairs = @dna_complement_pairs.invert

  def self.of_dna(dna)
    find_nucleotide_complement(dna, @dna_complement_pairs)
  end

  def self.of_rna(rna)
    find_nucleotide_complement(rna, @rna_complement_pairs)
  end

  def self.find_nucleotide_complement(nucleotides, complement_pairs)
    complement = ""

    nucleotides.each_char do |nucleotide|
      raise ArgumentError, 'Invalid Nucleotide: #{nucleotide}' unless complement_pairs[nucleotide]
      complement << complement_pairs[nucleotide].to_s
    end
    complement
  end
end
