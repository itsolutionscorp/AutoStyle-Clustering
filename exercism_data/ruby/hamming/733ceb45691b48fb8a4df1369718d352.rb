class Hamming
  attr_reader :a, :b

  class << self

  def compute(strand_a, strand_b)
    match_lengths(strand_a, strand_b)
    new_a = strand_a.chars
    new_b = strand_b.chars
    count_mutations(new_a, new_b)
  end

  def count_mutations(strand_a, strand_b)
    non_matches = 0
    strand_a.each_with_index do |letter, pos|
      if strand_b[pos] != letter
        non_matches += 1
      end
    end
    non_matches
  end

  def match_lengths(strand_a, strand_b)
    if strand_a.length > strand_b.length
      strand_a.slice!(strand_b.length..strand_a.length)
    elsif strand_b.length > strand_a.length
      strand_b.slice!(strand_a.length..strand_b.length)
    end
  end
  end
end
