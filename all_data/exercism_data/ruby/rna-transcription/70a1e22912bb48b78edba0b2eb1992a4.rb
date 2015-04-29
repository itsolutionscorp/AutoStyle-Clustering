class Complement
  def self.of_dna(strand)
    complement = ""
    strand.each_char {|nucleotide| complement += get_rna_complement_of(nucleotide)}
    complement
  end

  def self.of_rna(strand)
    complement = ""
    strand.each_char {|nucleotide| complement += get_dna_complement_of(nucleotide)}
    complement
  end

  private

  def self.get_rna_complement_of(nucleotide)
    DNA_RNA[nucleotide.to_s]
  end

  def self.get_dna_complement_of(nucleotide)
    RNA_DNA[nucleotide.to_s]
  end

  DNA_RNA = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_DNA = DNA_RNA.invert
end
