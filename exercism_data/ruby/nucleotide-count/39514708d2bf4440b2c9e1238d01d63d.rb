require 'forwardable'
class Nucleotide

  VALID_SYMBOLS = 'ATCG'

  def self.from_dna(*arg); new(*arg); end

  def initialize(dna)
    raise ArgumentError if dna.match(/[^#{VALID_SYMBOLS}]/)
    @dna = dna
    @nucleotides = Hash.new do |hash, key|
      current_count = 0
      @dna.each_char do |n|
        current_count += 1 if n == key
      end
      hash[key] = current_count
    end
    count_all
  end

  extend Forwardable
  def_delegator :@nucleotides, :[], :count
  def_delegator :@nucleotides, :each
  include Enumerable

  def histogram
    @nucleotides
  end
  
  private def count_all
    VALID_SYMBOLS.each_char {|n| count(n) }
  end

end
