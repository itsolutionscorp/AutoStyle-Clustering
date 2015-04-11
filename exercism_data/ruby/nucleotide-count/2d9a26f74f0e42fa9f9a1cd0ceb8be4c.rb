class DNA
  # not sure if nitpicking my last submission
  # prevented me from getting nitpicked...
  attr_reader :sequence, :nucleotides

  def initialize(sequence)
    @sequence = String(sequence)
    @nucleotides = Nucleotides.build
  end

  def count(nucleotide)
    return 0 if sequence.empty?
    raise ArgumentError if illegal_nucleotide?(nucleotide)
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    return nucleotides if sequence.empty?
    sequence.chars.each_with_object(nucleotides) do |nucleotide, count|
      count[nucleotide] += 1
    end
  end

  def illegal_nucleotide?(nucleotide)
    return if RNA::Nucleotides.build.include? nucleotide
    !nucleotides.include? nucleotide
  end

  class Nucleotides
    ADENOSINE = "A"
    CYTIDINE  = "C"
    GUANOSINE = "G"
    THYMIDINE = "T"

    def self.build
      Hash[constants.map { |v| [const_get(v), 0]}]
    end
  end
end

class RNA
  class Nucleotides
    ADENOSINE = "A"
    CYTIDINE  = "C"
    GUANOSINE = "G"
    URACIL = "U"

    def self.build
     Hash[constants.map { |v| [const_get(v), 0]}]
    end
  end
end
