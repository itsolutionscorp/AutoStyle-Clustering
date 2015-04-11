require 'pry'

class Hamming
  def self.compute(strand1, strand2)
    self.new(strand1, strand2).compare
  end

  def initialize(first_strand, second_strand)
    @first_strand = first_strand
    @second_strand = second_strand
  end

  def compare
    first_strand_length.inject(0) do |sum, index|
      @index = index

      if mutation_exists
        sum += 1
      end

      sum
    end
  end

  private

  attr_reader :first_strand, :second_strand, :index

  def first_strand_length
    (0..first_strand.size - 1)
  end

  def mutation_exists(index)
    second_strand_has_letter_at_position(index) && letters_dont_match(index)
  end

  def letters_dont_match(index)
    first_strand[index] != second_strand[index]
  end

  def second_strand_has_letter_at_position(index)
    !second_strand[index].nil?
  end
end
