module Complement

  # DNA <-> RNA maps
  DNA_RNA = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_DNA = DNA_RNA.invert

  def self.of_dna(dna_strand)
    get_complement_of(dna_strand, DNA_RNA)
  end

  def self.of_rna(rna_strand)
    get_complement_of(rna_strand, RNA_DNA)
  end

  private

  def self.get_complement_of(strand, map)
    strand.each_char.map { |nucleotide| map[nucleotide] }.join
  end

end
