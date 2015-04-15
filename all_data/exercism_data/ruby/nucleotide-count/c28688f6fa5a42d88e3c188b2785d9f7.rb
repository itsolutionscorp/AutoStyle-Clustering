class DNA
  attr_reader :nucleotide_counts

  def initialize(dna_string)
    @nucleotide_counts = count_nucleotides(dna_string)
  end

  def count(nucleotide)
    raise ArgumentError unless allowed_nucleotide?(nucleotide)
    nucleotide_counts.fetch(nucleotide, 0)
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
