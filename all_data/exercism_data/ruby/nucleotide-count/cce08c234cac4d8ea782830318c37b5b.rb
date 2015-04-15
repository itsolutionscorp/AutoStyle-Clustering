class DNA
  DNA_NUCLEOTIDES = %w[A T C G]
  URACIL = 'U'
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + [URACIL]

  attr_reader :dna_string

  def initialize(dna_string)
    ValidateDnaInput.new(dna_string).validate!

    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless counts.has_key? nucleotide 
    counts[nucleotide]
  end

  def nucleotide_counts
    counts.reject{|k,_| k == URACIL}
  end

  private
  def counts
    @counts ||= ALL_NUCLEOTIDES.reduce({}) {|counts, n|
      counts[n] = dna_string.count n 
      counts
    }
  end
  
  class ValidateDnaInput
    attr_reader :dna_string

    def initialize dna_string
      @dna_string = dna_string
    end

    def validate!
      raise ArgumentError, "Received: #{dna_string}, "\
          "but only DNA nucleotides are allowed: "\
          "#{DNA_NUCLEOTIDES}" unless includes_dna_only?(dna_string) 
    end

    def includes_dna_only?(dna_string)
      dna_string.empty? || dna_string.chars.all?{|c| DNA_NUCLEOTIDES.include? c}
    end
  end
end
