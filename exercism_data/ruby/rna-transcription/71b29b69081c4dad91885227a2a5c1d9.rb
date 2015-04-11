class Complement
  RNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  DNA_COMPLEMENTS = RNA_COMPLEMENTS.invert

  def self.of_dna(strand)
    raise ArgumentError, 'DNA strand cannot contain uracil' if strand.include?('U')
    strand.chars.map{|n| rna_transcribe(n)}.join('')
  end

  def self.of_rna(strand)
    raise ArgumentError, 'RNA strand cannot contain thymine' if strand.include?('T')
    strand.chars.map{|n| dna_transcribe(n)}.join('')
  end

  def self.rna_transcribe(nucleotide)
    RNA_COMPLEMENTS[nucleotide]
  end

  def self.dna_transcribe(nucleotide)
    DNA_COMPLEMENTS[nucleotide]
  end

end
