module Nucleotide
  NUCLEOTIDES = ["A", "T", "G", "C"]

  def self.valid_nucleotide?(char)
    NUCLEOTIDES.include?(char)
  end
end

class DNA
  def initialize(string)
    @string = string
    validate_string
  end

  def count(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a nucleotide" unless Nucleotide.valid_nucleotide?(nucleotide)
    @string.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotide::NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  private

  def validate_string
    @string.each_char { |char| raise ArgumentError, "Invalid string" unless Nucleotide.valid_nucleotide?(char) }
  end
end
