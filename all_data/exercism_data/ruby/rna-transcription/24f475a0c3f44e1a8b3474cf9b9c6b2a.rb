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
    RNA_COMPLEMENT[nucleotide.to_s]
  end

  def self.get_dna_complement_of(nucleotide)
    DNA_COMPLEMENT[nucleotide.to_s]
  end

  RNA_COMPLEMENT = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  DNA_COMPLEMENT = {"G" => "C", "C" => "G", "T" => "A", "A" => "T", "U" => "A"}
end
