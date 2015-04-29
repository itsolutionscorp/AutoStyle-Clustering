# Extend string to implement a to_a method, this has the nice
# affect of allowing DNA to take any sort of class that can
# be cast to an array.  This means we could do:
#  DNA.new(['G', 'G', 'G'])
#   or
#  DNA.new('GGG')
# I think this is worth the cost of extending the core String
# class. 
class String
  def to_a
    chars.to_a
  end
end

class DNA
  VALID_NUCLEOTIDES = %w{ A T C G }

  def self.valid_dna?(dna)
    invalid_componets = dna.to_a.reject do |component|
      VALID_NUCLEOTIDES.include? component
    end
    invalid_componets.empty? 
  end

  attr_reader :components

  def initialize(dna)
    raise ArgumentError unless self.class.valid_dna?(dna)
    @components = dna.to_a
  end

  def count(nucleotide)
    raise ArgumentError unless self.class.valid_dna?(nucleotide.to_s)
    components.count { |component| component == nucleotide }
  end

  def nucleotide_counts
    VALID_NUCLEOTIDES.each.inject({}) do |nucleotides_hash, nucleotide|
      nucleotides_hash[nucleotide] = count(nucleotide)
      nucleotides_hash
    end
  end
end
