class Complement
  def self.of_dna nucleotides
    DNA.new(nucleotides).complement
  end

  def self.of_rna nucleotides
    RNA.new(nucleotides).complement
  end
end

class Strand
  def initialize nucleotides
    @nucleotides = nucleotides.upcase
  end

  def complement
    @nucleotides
    .chars
    .inject(""){|complement, nucleotide| complement + transcribe(nucleotide)}
  end

  def transcribe nucleotide
    self.class::TRANSCRIPTION[nucleotide]    
  end
end

class DNA < Strand
  TRANSCRIPTION = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
end

class RNA < Strand
  TRANSCRIPTION = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
end
