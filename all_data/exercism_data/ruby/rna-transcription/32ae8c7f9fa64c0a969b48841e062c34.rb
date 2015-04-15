module Complement
  COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    transcribe(strand, COMPLEMENTS)
  end

  def self.of_rna(strand)
    transcribe(strand, COMPLEMENTS.invert)
  end

  private

  def self.transcribe(strand, complements)
    0.upto(strand.length - 1) do |i|
      raise ArgumentError unless complements.key?(strand[i])
      strand[i] = complements[strand[i]]
    end
    strand
  end
end
