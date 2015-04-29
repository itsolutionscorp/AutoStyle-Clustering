module Hamming
  def self.compute(strand,other_strand)
    bases_to_check = [strand.size,other_strand.size].min
    differences_found = 0
    bases_to_check.times do |position|
      differences_found += 1 if strand[position] != other_strand[position]
    end
    differences_found
  end
end
