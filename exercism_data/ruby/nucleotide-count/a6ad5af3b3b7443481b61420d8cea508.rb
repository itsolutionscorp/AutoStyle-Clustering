class DNA
  DNA_SYMBOLS = "ATCG"
  ALL_SYMBOLS = DNA_SYMBOLS + "U"

  def initialize dna
    raise ArgumentError unless dna.tr_s(DNA_SYMBOLS, '').empty?
    @dna = dna
  end

  def count nucleotide
    raise ArgumentError unless ALL_SYMBOLS.include? nucleotide
    @dna.count nucleotide
  end

  def nucleotide_counts
    Hash[DNA_SYMBOLS.chars.zip(Array.new(DNA_SYMBOLS.size, 0))].tap do |result|
      @dna.chars.each do |nucleotide|
        result[nucleotide] += 1
      end
    end
  end
end
