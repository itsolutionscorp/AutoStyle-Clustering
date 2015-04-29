module Hamming
  def self.compute(first_strand, second_strand)
    [first_strand.chars, second_strand.chars].transpose.count do |first_strand_base, second_strand_base|
      first_strand_base != second_strand_base
    end
  end
end
