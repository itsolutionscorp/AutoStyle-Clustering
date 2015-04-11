class DNA
  attr_reader :strand, :nucleotide_counts

  def initialize(strand)
    @strand = strand
    @nucleotide_counts ||= {
                              'A' => strand.count('A'),
                              'T' => strand.count('T'),
                              'C' => strand.count('C'),
                              'G' => strand.count('G')
                            }
  end

  def count(nucleotide) 
    raise ArgumentError, "Invalid nucleotide" unless valid?(nucleotide)
    nucleotide_counts[nucleotide] || 0
  end

  def valid?(nucleotide)
    ["A", "T", "C", "G", "U"].include?(nucleotide)
  end
end
