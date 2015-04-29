#hamming.rb
class Hamming
  def self.compute(dna_strand_a, dna_strand_b)

    hamming_distance = 0

    [dna_strand_a.chars.to_a, dna_strand_b.chars.to_a].transpose.each do |a,b|
      if a != b
        hamming_distance += 1
      end
    end

    hamming_distance
  end
end
