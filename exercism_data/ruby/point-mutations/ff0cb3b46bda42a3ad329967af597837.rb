class DNA
  def initialize(string)
    @string = string
  end

  def hamming_distance(other_string)
    other_dna  = DNA.new(other_string)

    mutation_count(other_dna)
  end

  def trim(trim_length)
    @string.slice(0...trim_length)
  end

  def length
    @string.length
  end

  private

  def mutation_count(other_dna)
    max_length = [length, other_dna.length].min

    trim(max_length).chars.zip(other_dna.trim(max_length).chars).count { |result| result.uniq.length > 1 }
  end
end
