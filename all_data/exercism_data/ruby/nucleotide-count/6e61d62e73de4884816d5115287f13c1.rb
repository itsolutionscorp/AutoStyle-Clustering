class Nucleotide
  attr_reader :nucleobase

  def initialize(char)
    @nucleobase = char
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
    def parse(str)
      return str.chars.inject([]) { |list, char| list << new(char) }
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
    @nucleotides = @nucleotide_class.parse(@raw)
    raise ArgumentError unless @nucleotides.all? { |n| n.valid?(:dna) }
  end

  def count(char)
    nucleotide = @nucleotide_class.new(char)
    raise ArgumentError unless nucleotide.valid?
    return @nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    return @nucleotide_class.dna.each_with_object({}) do |base, totals|
      totals[base] = count(base)
    end
  end
end
