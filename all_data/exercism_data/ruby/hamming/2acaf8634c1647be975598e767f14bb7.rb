class DNA
  attr_reader :dna

  def initialize(dna)
    @dna = dna
  end

  def hamming_distance(string)
    dna_set(string).inject(0) do |count, chars|
      count += different?(chars) ? 1 : 0
    end
  end

  private
  def dna_set(string)
    dna.chars.slice(0, string.length).zip(string.chars)
  end

  def different?(chars)
    chars.uniq.length != 1
  end
end
