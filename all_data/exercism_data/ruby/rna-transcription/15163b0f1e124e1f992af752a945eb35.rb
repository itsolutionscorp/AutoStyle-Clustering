class Complement

  def self.of_dna(nucleotide)
    @dna_rna = {"A" => "U", "C" => "G", "G" => "C", "T" => "A"}
    find_complement(nucleotide, @dna_rna)
  end

  def self.of_rna(nucleotide)
    @rna_dna = {"A" => "T", "C" => "G", "G" => "C", "U" => "A"}
    find_complement(nucleotide, @rna_dna)
  end

  def self.find_complement(nucleotide, strand_type)
    initial_strand = nucleotide.chars
    complement_strand = ""

    initial_strand.each { |n|
      complement_strand += strand_type[n]
    }

    complement_strand
  end

end
