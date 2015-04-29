class Hamming
  def self.compute strand_one, strand_two
    distance = 0

    comparison_length = strand_one.length < strand_two.length ? strand_one.length : strand_two.length

    comparison_length.times do |index|
      distance += 1 if strand_one[index] != strand_two[index]
    end

    distance
  end
end
