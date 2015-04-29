class Complement
  @@dna_complements = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna_strand)
    complement_of_strand(dna_strand, dna_transcriber)
  end

  def self.of_rna(rna_strand)
    complement_of_strand(rna_strand, rna_transcriber)
  end

  def self.complement_of_strand(strand, transcriber)
    strand.each_char.map { |nucleotide| transcriber.call(nucleotide) }.join
  end

  def self.dna_transcriber
    proc { |nucleotide| @@dna_complements[nucleotide] }
  end

  def self.rna_transcriber
    proc { | nucleotide| @@dna_complements.key(nucleotide) }
  end
end
