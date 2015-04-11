require 'pry'

class Hamming

  def self.compute(first, second)
    @first, @second = first, second
    set_strand_position_by_length(first, second) unless strands_are_equal?
    first_strand, second_strand = @first.scan(/\w/), @second.scan(/\w/)

    count_the_distance(first_strand, second_strand)
  end

private
  def self.count_the_distance(first_strand, second_strand)
    count, i = 0, 0
    while i < first_strand.count do
      count += 1 unless first_strand[i] == second_strand[i]
      i += 1
    end

    count
  end

  def self.set_strand_position_by_length(first, second)
    @first = first.length < second.length ? first : second
    @second = first.length > second.length ? first : second
  end

  def self.strands_are_equal?
    @first.length == @second.length
  end

end
