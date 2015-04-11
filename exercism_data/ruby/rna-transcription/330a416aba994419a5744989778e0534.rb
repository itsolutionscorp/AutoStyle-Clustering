module Complement

  DNA_NUCLEOTIDES = ['G', 'C', 'T', 'A']
  RNA_NUCLEOTIDES = ['C', 'G', 'A', 'U']

  DIRECTIONS = {
    dna: [DNA_NUCLEOTIDES, RNA_NUCLEOTIDES],
    rna: [RNA_NUCLEOTIDES, DNA_NUCLEOTIDES]
  }

  def self.of_dna(sequence)
    self.of(:dna, sequence)
  end

  def self.of_rna(sequence)
    self.of(:rna, sequence)
  end

  private

  def self.of(type, sequence)
    res_sequence = sequence.chars.map do |char|
      from_nucleotides, to_nucleotides = DIRECTIONS[type]
      to_nucleotides[from_nucleotides.index(char)]
    end
    res_sequence.join('')
  end
end
