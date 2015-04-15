module Nucleotide
  VALID_NUCLEOTIDES = ["Adenine", 
                       "Guanine", 
                       "Cytosine", 
                       "Thymine", 
                       "Uracil"]

  def self.valid?(possible_nucleotide)
    abbreviations.include? possible_nucleotide
  end

  def self.for_dna
    abbreviations.select { |x| x != "U" }
  end

  private
  def self.abbreviations
    VALID_NUCLEOTIDES.map { |n| n.chars.first }
  end
end

class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand.split("")
  end

  def nucleotide_counts
    strand.each_with_object(empty_strand_counts) do |element, counts| 
      counts[element] += 1 if valid_nucleotide?(element)
    end
  end

  def count(nucleotide)
    raise ArgumentError.new("#{nucleotide} is not a valid choice.") unless valid_nucleotide?(nucleotide)
    strand.count { |element| element == nucleotide } 
  end
  
  private

  def empty_strand_counts
    Nucleotide.for_dna.
      each_with_object({}) { |n,count| count[n] = 0 }
  end

  def valid_nucleotide?(element)
    Nucleotide.valid? element
  end
end
