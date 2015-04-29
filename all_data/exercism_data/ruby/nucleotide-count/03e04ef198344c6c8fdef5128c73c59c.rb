require 'set'

class DNA
  attr_reader :dna, :nucleotide_counts, :valid_nucleotides, :valid_dna

  def initialize dna
    @valid_nucleotides = Set.new "ATCGU".chars
    @valid_dna         = Set.new "ATCG".chars

    @dna               = parse_dna dna
    @nucleotide_counts = count_nucleotides @dna
 end

  def count nucleotide
    check_for_valid_nucleotide nucleotide
    dna.count nucleotide
  end

  private

  def parse_dna dna
    check_for_valid_dna dna.chars
    dna.chars
  end

  def check_for_valid_nucleotide nucleotide
    raise ArgumentError unless valid_nucleotides.include? nucleotide
  end

  def check_for_valid_dna dna
    raise ArgumentError unless valid_dna.superset? dna.to_set
  end

  def count_nucleotides dna
    dna.each_with_object( base_nucleotides ){ | nucleotide, counts |
      counts[nucleotide] += 1
    }
  end

  def base_nucleotides
    valid_dna.each_with_object( Hash.new ){| nucleotide, nucleotides |
      nucleotides[nucleotide] = 0
    }
  end

end
