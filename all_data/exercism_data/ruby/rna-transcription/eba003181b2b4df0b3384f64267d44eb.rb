class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    complement(DNA_TO_RNA, strand)
  end

  def self.of_rna(strand)
    complement(RNA_TO_DNA, strand)
  end

  private

  def self.complement(chash, strand)
    strand.chars.map{ |nucleotide| chash[nucleotide] }.join
  end
end
