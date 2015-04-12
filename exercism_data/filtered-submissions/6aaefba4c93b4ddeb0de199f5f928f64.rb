module Hamming
  def compute(strand_one, strand_two)
    differences = 0
    strand_one.size.times do |i|
      break if (strand_one[i].nil? or strand_two[i].nil?)
      differences += 1 if strand_one[i] != strand_two[i]
    end
    differences
  end
end

include Hamming
