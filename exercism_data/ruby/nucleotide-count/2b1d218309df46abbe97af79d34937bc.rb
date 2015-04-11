# encoding: utf-8

class DNA
  NUCLEOTIDES = %w(A T C G)

  def initialize(sequence)
    @sequence = sequence
    raise ArgumentError unless valid?
  end

  def nucleotide_counts
    counts = Hash[NUCLEOTIDES.zip(Array.new(NUCLEOTIDES.size, 0))]
    @sequence.each_char do |char|
      counts[char] += 1
    end

    counts
  end

  def count(letter)
    raise ArgumentError unless valid_nucleotide?(letter)
    nucleotide_counts.fetch(letter, 0)
  end

  def valid_nucleotide?(letter)
    %w(A T C G U).include?(letter)
  end

  def valid?
    @sequence.chars.all? { |c| valid_nucleotide?(c) }
  end
end
