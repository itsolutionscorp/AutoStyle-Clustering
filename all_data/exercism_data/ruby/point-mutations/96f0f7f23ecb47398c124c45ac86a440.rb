class DNA
  include Enumerable

  def initialize(string)
    @string = string
  end

  def each(&block)
    nucleotides.each(&block)
  end

  def hamming_distance(other_string)
    mutation_count(DNA.new(other_string))
  end

  private

  def nucleotides
    @string.chars
  end

  def mutation_count(other_dna)
    zip(other_dna).count { |original, other| original && other && original != other }
  end
end
