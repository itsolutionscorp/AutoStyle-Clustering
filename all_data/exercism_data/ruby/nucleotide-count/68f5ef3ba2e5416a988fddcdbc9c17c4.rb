class Nucleotide
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def self.from_dna(strand)
    if strand.match(/^[atcg]*$/i)
      Nucleotide.new(strand)
    else
      raise ArgumentError
    end
  end

  def count(letter)
    strand.count(letter)
  end

  def histogram
    nucleotide = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    nucleotide.keys.each { |letter| nucleotide[letter] = count(letter) }
    nucleotide
  end
end
