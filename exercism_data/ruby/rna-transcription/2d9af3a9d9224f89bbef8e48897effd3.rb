class Complement

  DNA_TO_RNA = { 
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna strand
    transcribe(strand.chars, DNA_TO_RNA).join

  end

  def self.transcribe nucleotides, transformation
    nucleotides.map do |nucleotide|
      transformation[nucleotide]
    end
  end

  def self.of_rna strand
    transcribe(strand.chars, DNA_TO_RNA.invert).join
  end

end
