class Nucleotide
  attr_reader :nucleobase

  def initialize(char, molecular_type=:any)
    @nucleobase = char
    raise ArgumentError unless valid?(molecular_type)
  end

  def valid?(molecular_type=:any)
    return self.class.send(molecular_type).include?(nucleobase)
  end

  def ==(other)
    return nucleobase == other.nucleobase
  end

  def to_str
    return nucleobase
  end

  # Class methods
  class << self
    def parse(str, molecular_type=:any)
     return str.chars.inject([]) { |list, c| list << new(c, molecular_type) }
    end

    def any
      return %w[A C G T U]
    end

    def dna
      return any.reject { |base| base == 'U' }
    end

    def rna
      return any.reject { |base| base == 'T' }
    end
  end
end

class DNA
  def initialize(str, opts={})
    @raw = str
    @nucleotide_class = opts.fetch(:nucleotide_class, Nucleotide)
    @nucleotides = @nucleotide_class.parse(@raw, :dna)
  end

  def count(char)
    return @nucleotides.count(@nucleotide_class.new(char))
  end

  def nucleotide_counts
    return @nucleotide_class.dna.each_with_object({}) do |base, totals|
      totals[base] = count(base)
    end
  end
end
