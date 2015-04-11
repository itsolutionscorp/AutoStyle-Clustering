class Hamming
  def self.compute(strand_1, strand_2)
    distance = 0

    strand_1.split('').each_with_index do |character, index|
      distance += 1 if character != strand_2[index]
    end

    distance
  end
end
