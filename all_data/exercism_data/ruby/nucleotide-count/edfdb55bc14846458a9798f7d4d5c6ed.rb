class DNA
  DNA_NUCLEOTIDES = %w[A T C G]
  URACIL = 'U'
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + [URACIL]

  attr_reader :dna_string

  def initialize(dna_string)
    ValidateDnaInput.new.validate dna_string

    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless count_of_all_nucleotides.has_key? nucleotide 
    count_of_all_nucleotides[nucleotide]
  end

  def nucleotide_counts
    count_of_all_nucleotides.reject{|k,_| k == URACIL}
  end

  private
  def count_of_all_nucleotides 
    @count_of_all_nucleotides ||= NucleotideCounter.new.count_all_nucleotides_in dna_string
  end

  class NucleotideCounter
    def count_all_nucleotides_in dna_string
      ALL_NUCLEOTIDES.reduce({}) {|counts, n|
        counts[n] = dna_string.count n 
        counts
      }
    end
  end 
  
  class ValidateDnaInput
    def validate(dna_string)
      raise ArgumentError, "Received: #{dna_string}, "\
          "but only DNA nucleotides are allowed: "\
          "#{DNA_NUCLEOTIDES}" unless includes_dna_only?(dna_string) 
    end

    def includes_dna_only?(dna_string)
      dna_string.empty? || dna_string.chars.all?{|c| DNA_NUCLEOTIDES.include? c}
    end
  end
end
