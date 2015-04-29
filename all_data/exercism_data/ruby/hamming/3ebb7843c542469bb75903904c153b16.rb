require 'pry'

class Hamming
  def self.compute(first_strand, second_strand)
    strand_1 = first_strand.chars
    strand_2 = second_strand.chars
    shorter_strand = compare_strand_lengths(strand_1, strand_2).first
    longer_strand = compare_strand_lengths(strand_1, strand_2).last

    shorter_strand.each_with_index.count do |element, index|
      element != longer_strand[index]
    end
  end

  def self.compare_strand_lengths(first_strand, second_strand)
    [first_strand, second_strand].sort_by do |strand|
      strand.length
    end
  end
end
