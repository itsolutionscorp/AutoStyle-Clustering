require 'forwardable'
class Nucleotide

  VALID_SYMBOLS = 'ATCG'

  def initialize(dna)
    raise ArgumentError if dna.match(/[^#{VALID_SYMBOLS}]/)
    @dna = dna
  end
  
  def histogram 
    @nucleotides ||= Hash.new.tap do |nucleotides|
      VALID_SYMBOLS.each_char do |current_symbol| 
        current_count = 0
        @dna.each_char do |n|
          current_count += 1 if n == current_symbol
        end
        nucleotides[current_symbol] = current_count
      end
    end
  end

  extend Forwardable
  def_delegator :histogram, :[], :count
  def_delegator :histogram, :each
  include Enumerable

  class <<self
    alias :from_dna :new
  end

end
