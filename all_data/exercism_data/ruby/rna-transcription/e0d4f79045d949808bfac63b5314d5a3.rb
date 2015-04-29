class Complement
  def self.of_dna(strand)
    self.convert(strand, self.dna_to_rna)
  end

  def self.of_rna(strand)
    self.convert(strand, self.rna_to_dna)
  end

  def self.convert(strand, conversion)
      strand.each_char.with_object(new_strand = "") { |nucleotide| new_strand << conversion[nucleotide] }
    rescue
      raise ArgumentError
  end

  def self.dna_to_rna
    {"A" => "U", "G" => "C", "C" => "G", "T" => "A" }
  end

  def self.rna_to_dna
    {"U" => "A", "C" => "G", "G" => "C", "A" => "T" }
  end
end
