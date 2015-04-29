class Hamming
  attr_reader :string, :other_string

  def initialize(string, other_string)
    @string = string
    @other_string = other_string
  end

  def compute
    hamming = 0
    shortest_string.length.times do |index|
      hamming += 1 unless same_character_at? index
    end
    hamming
  end

  def shortest_string
    if string.length < other_string.length
      string
    else
      other_string
    end
  end

  def same_character_at?(index)
    string[index] == other_string[index]
  end

  def self.compute(dna_strand, other_dna_strand)
    Hamming.new(dna_strand, other_dna_strand).compute
  end
end
