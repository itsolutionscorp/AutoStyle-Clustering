class Complement
  def self.of_dna(strand)
    map_string(strand) do |dna_nucleotide|
      dna_to_rna dna_nucleotide
    end
  end

  def self.of_rna(strand)
    map_string(strand) do |rna_nucleotide|
      rna_to_dna rna_nucleotide
    end
  end

private
  def self.dna_to_rna_map
    {
      G: "C",
      C: "G",
      T: "A",
      A: "U"
    }
  end

  def self.rna_to_dna_map
    {
      G: "C",
      C: "G",
      A: "T",
      U: "A"
    }
  end

  def self.dna_to_rna(dna_string)
    dna_to_rna_map[dna_string.to_sym]
  end

  def self.rna_to_dna(rna_string)
    rna_to_dna_map[rna_string.to_sym]
  end

  def self.map_string(string, &block)
    string.each_char.map(&block).join("")
  end
end
