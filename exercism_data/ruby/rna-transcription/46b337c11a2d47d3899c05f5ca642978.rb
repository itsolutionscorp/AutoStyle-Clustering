class Complement
  DNA_TO_RNA = {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(nucleotides)
    lookup(nucleotides, DNA_TO_RNA)
  end

  def self.of_rna(nucleotides)
    lookup(nucleotides, DNA_TO_RNA.invert)
  end

  private

  def self.lookup(nucleotides, hash)
    nucleotides.chars.map { |c| hash[c] }.join 
  end
end
