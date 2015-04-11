class DNA
  attr_accessor :dna_sequence

  def initialize dna_sequence_characters
    @dna_sequence = DNA_Sequence.new(dna_sequence_characters)
  end

  def count(symbol_to_find)
    begin
      raise ArgumentError.new unless Nucleotide.new(dna_sequence).valid? symbol_to_find
    rescue
      dna_sequence.count symbol_to_find
    end
    dna_sequence.count symbol_to_find
  end

  def nucleotide_counts
    Nucleotide.new(dna_sequence).count_nucleotides
  end
end

class Nucleotide

  attr_accessor :dna_sequence
  attr_accessor :nucleotides

  def initialize dna_sequence
    @dna_sequence = dna_sequence
    @nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def count_nucleotides
    dna_sequence.sequence.chars.each_with_object(nucleotides) { |symbol, hash| nucleotides[symbol] += 1 }
  end

  def valid? symbol
    nucleotides.has_key? symbol
  end
end

class DNA_Sequence

  attr_accessor :sequence

  def initialize dna_sequence
    @sequence = dna_sequence.to_s
  end

  def count(symbol_to_find)
    (sequence.chars.select { |symbol_in_sequence| symbol_to_find == symbol_in_sequence }).length
  end

  def to_s
    self.to_s.strip
  end
end
