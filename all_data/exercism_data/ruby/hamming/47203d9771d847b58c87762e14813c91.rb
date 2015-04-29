module Hamming
  def self.compute(a_strand, b_strand)
    a_strand.chars.zip(b_strand.chars).inject(0) do |ham_distance, pair|
      ham_distance += pair.compact.uniq.length - 1
    end
  end
end
