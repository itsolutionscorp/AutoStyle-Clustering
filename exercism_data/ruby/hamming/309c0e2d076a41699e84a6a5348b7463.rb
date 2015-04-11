require 'pry'

class Hamming
  def self.compute(strand1, strand2)
    self.new(strand1, strand2).compare
  end

  def initialize(first_strand, second_strand)
    @first_strand = first_strand
    @second_strand = second_strand
    @score = 0
  end

  def compare
    first_strand_length.each do |index|
      if second_strand_has_letter_at_position(index)
        compare_letters_at_position(index)
      end
    end

    @score
  end

  private

  attr_reader :first_strand, :second_strand

  def first_strand_length
    (0..first_strand.size - 1)
  end

  def second_strand_has_letter_at_position(index)
    !second_strand[index].nil?
  end

  def compare_letters_at_position(index)
    if first_strand[index] != second_strand[index]
      @score += 1
    end
  end
end
