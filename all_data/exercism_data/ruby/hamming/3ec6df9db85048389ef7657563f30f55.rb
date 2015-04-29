class Hamming
  def self.compute strand_one, strand_two
    distance = 0

    comparison_length = strand_one.length < strand_two.length ? strand_one.length : strand_two.length

    comparison_length.times do |index|
      if strand_one[index] != strand_two[index]
        distance += 1
      end
    end

    distance
  end
end
