class Hamming
  def compute(strand1, strand2)
    distance = 0
    min_length = [strand1, strand2].min_by(&:length).length

    min_length.times do |i|
      distance += 1 if strand1[i] != strand2[i]
    end
    distance
  end
end
