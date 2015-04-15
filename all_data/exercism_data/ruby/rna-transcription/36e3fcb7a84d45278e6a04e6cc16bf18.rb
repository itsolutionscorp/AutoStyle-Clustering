class Complement

  COMPLEMENT = { 'G' => 'C',
                 'C' => 'G',
                 'T' => 'A',
                 'A' => 'U' }

  def self.of_dna(dna)
    new(dna).transcribe.join
  end

  def self.of_rna(rna)
    new(rna).detranscribe.join
  end

  def initialize(strand)
    @strand = strand.chars
  end

  def transcribe
    @strand.map do |nucleotide|
      COMPLEMENT[nucleotide]
    end
  end

  def detranscribe
    @strand.map do |nucleotide|
      COMPLEMENT.key(nucleotide)
    end
  end

end
