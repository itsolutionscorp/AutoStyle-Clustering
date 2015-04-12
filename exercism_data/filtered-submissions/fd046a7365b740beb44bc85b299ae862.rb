class Hamming
  def compute(strand1, strand2)
    hamming_distance = 0
    strand1.chars.each_with_index do |char, i|
      break if i == strand2.length
      hamming_distance += 1 unless char == strand2.chars[i]
    end
    hamming_distance
  end
end
