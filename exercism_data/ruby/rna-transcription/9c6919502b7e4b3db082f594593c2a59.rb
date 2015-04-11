class Complement
  @nucleotides = { 'C' => 'G',
                   'G' => 'C',
                   'T' => 'A',
                   'A' => 'U' }

  def self.of_dna(strand)
    transcribe(strand, @nucleotides)
  end

  def self.of_rna(strand)
    transcribe(strand, @nucleotides.invert)
  end

  def self.transcribe(strand, nucleotides)
    strand.chars.map {|n|
      nucleotides[n]
    }.join
  end
end
