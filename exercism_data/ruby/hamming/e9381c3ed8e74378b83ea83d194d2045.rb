class Hamming
  def self.compute(strand_one, strand_two)
    hamming_difference = 0
    strands = [strand_one, strand_two]
    min_length = minimum_length(strands)

    min_length.times do |i|
      hamming_difference += 1 if base_pair_difference?(strands, i)
    end

    hamming_difference
  end

  def self.base_pair_difference?(strands, i)
    strands[0][i] != strands[1][i]
  end

  def self.minimum_length(strands)
    strands.map{|strand| strand.length}.min
  end
end
