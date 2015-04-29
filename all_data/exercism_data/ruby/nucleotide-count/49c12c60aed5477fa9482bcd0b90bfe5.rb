require "forwardable"

class Nucleotide
  extend Forwardable

  def initialize(dna)
    @dna = dna
  end

  def histogram
    result = { "A" => 0, "T" => 0, "C" => 0, "G" => 0 }
    @dna.each_char.with_object(result) do |nucleotide, result|
      result[nucleotide] += 1
      result
    end
  end

  def self.from_dna(dna)
    raise ArgumentError unless dna.empty? || dna.match(/\A[ATCG]+$/)
    new(dna)
  end

  def_delegator :@dna, :count
end
