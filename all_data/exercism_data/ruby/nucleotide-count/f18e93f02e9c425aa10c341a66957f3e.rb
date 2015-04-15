class Nucleotide
  attr_reader :histogram

  def self.from_dna(dna_string)
    new(dna_string)
  end

  def initialize(dna_string)
    @histogram = count_nucleotides(dna_string)
  end

  def count(nucleotide)
    raise ArgumentError unless allowed_nucleotide?(nucleotide)
    histogram.fetch(nucleotide, 0)
  end

  private
  def count_nucleotides(dna_string) 
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    dna_string.chars.each do |nucleotide|
      raise ArgumentError unless dna?(nucleotide)
      counts[nucleotide] += 1
    end
    counts
  end

  def allowed_nucleotide?(nucleotide)
    "GACTU".include?(nucleotide)
  end

  def dna?(nucleotide)
    "GACT".include?(nucleotide)
  end
end
