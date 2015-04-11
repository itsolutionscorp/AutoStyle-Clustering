class DNA
  attr_accessor :strand

  def initialize(strand)
    @strand = strand
  end

  def nucleotide_counts
    @nucleotide_counts ||= count_nucleotides(strand)
  end

  def count(nucleotide)
    validate nucleotide
    nucleotide_counts.fetch(nucleotide, 0)
  end

  private
  def validate(letter)
    Nucleotide.from_initial(letter).to_s
  end

  def nucleotides
    strand.chars
  end

  def count_nucleotides(strand)
    nucleotides.each_with_object(empty_count) do |nucleotide, new_count|
      new_count[nucleotide] += 1
    end
  end

  def empty_count
    {
      'A' => 0,
      'T' => 0,
      'G' => 0,
      'C' => 0
    }
  end
end

class Nucleotide
  attr_reader :initial

  VALID_NUCLEOTIDES = ['A', 'C', 'G', 'T', 'U']

  def self.from_initial(letter)
    raise ArgumentError unless VALID_NUCLEOTIDES.include? letter
    Nucleotide.new(letter)
  end

  def initialize(letter)
    @initial = letter
  end

  def to_s
    initial
  end
end
