class Nucleotide

  attr_reader :dna, :dna_map

  def initialize dna
    @dna = dna
    @dna_map = {
    'A' => 0,
    'T' => 0,
    'C' => 0,
    'G' => 0,
  }
    parse_dna
  end

  # Class methods

  def self.from_dna dna
    validate dna
    new dna
  end

  # Instance methods

  def count nucleotide
    dna_map[nucleotide]
  end

  def histogram
    dna_map
  end
  
  private

  def parse_dna
    dna.chars.each do |char|
      dna_map[char] += 1
    end
  end

  def self.validate dna
    if dna =~ (/[^ATCG ]+/)
      raise ArgumentError
    end
  end
end
