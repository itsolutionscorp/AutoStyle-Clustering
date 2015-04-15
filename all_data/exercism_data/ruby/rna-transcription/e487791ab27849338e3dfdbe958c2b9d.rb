class Complement

  # keys are DNA, values are RNA complements
  NUCLEOTIDES = {
    G: :C,
    C: :G,
    T: :A,
    A: :U
  }

  # Convert DNA input to RNA output
  def self.of_dna(strand)
    # strand.chars.map(&method(:to_rna)).join
    strand.chars.map { |c| NUCLEOTIDES[c.upcase.to_sym] }.join
  end

  # Convert RNA input to DNA output
  def self.of_rna(strand)
    # strand.chars.map(&method(:to_dna)).join
    strand.chars.map { |c| NUCLEOTIDES.invert[c.upcase.to_sym] }.join
  end

  # # Convert a single DNA character to RNA equivalent
  # def self.to_rna(char)
  #   NUCLEOTIDES[valid_nucleotide(char)].to_s
  # end

  # # Convert a single RNA character to DNA equivalent
  # def self.to_dna(char)
  #   NUCLEOTIDES.key(valid_nucleotide(char, as: :rna)).to_s
  # end

  # # Raises an error if input doesn't match expectations
  # def self.valid_nucleotide(n, as: :dna)
  #   n = n.upcase.to_sym
  #   ref = case as
  #         when :dna
  #           NUCLEOTIDES[n]
  #         when :rna
  #           NUCLEOTIDES.key(n)
  #         else
  #           fail(ArgumentError, 'Bad nucleotide lookup; must use either :dna or :rna')
  #         end
  #   # Break out of this validation unless something's wrong
  #   return n unless ref.nil?
  #   fail ArgumentError, 'You must only provide a single character string or symbol that represents a DNA nucleotide'
  # end
end
