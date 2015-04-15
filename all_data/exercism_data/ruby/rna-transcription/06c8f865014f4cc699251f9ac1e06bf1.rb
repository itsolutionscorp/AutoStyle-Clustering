class Complement
  def self.of_dna(rna)
    self.transcribe_strand(rna, 'rna')
  end

  def self.of_rna(dna)
    self.transcribe_strand(dna, 'dna')
  end

  def self.transcribe_strand(strand, base)
    strand.each_char.inject("") do |t, nucleotide|
      t << self.find_nucleotide_complement_from(nucleotide, base)
    end
  end

private

  def self.find_nucleotide_complement_from(nucleotide, type)
    # Key is DNA, Value is RNA
    complements = {'G' => 'C',
                   'C' => 'G',
                   'T' => 'A',
                   'A' => 'U'}
    return type == 'dna' ? complements.key(nucleotide) : complements[nucleotide]
  end

end
