class Complement

  # keys are DNA, values are RNA complements
  TRANSCRIPTIONS = {
    G: :C,
    C: :G,
    T: :A,
    A: :U
  }

  # Convert DNA input to RNA output
  def self.of_dna(strand)
    strand.chars.map(&method(:to_rna)).join
  end

  # Convert RNA input to DNA output
  def self.of_rna(strand)
    strand.chars.map(&method(:to_dna)).join
  end

  # Convert a single DNA character to RNA equivalent
  def self.to_rna(char)
    char = char.upcase.to_sym
    fail(ArgumentError, 'You must only provide a single character string or symbol that represents a DNA nucleotide') if
      TRANSCRIPTIONS[char].nil?
    TRANSCRIPTIONS[char].to_s
  end

  # Convert a single RNA character to DNA equivalent
  def self.to_dna(char)
    char = char.upcase.to_sym
    fail(ArgumentError, 'You must only provide a single character string or symbol that represents an RNA nucleotide') if
      TRANSCRIPTIONS.key(char).nil?
    TRANSCRIPTIONS.key(char).to_s
  end
end
