class Hamming

  def self.compute(first,second)
    string_length = first.length
    hamming_distance = 0
    (0..string_length).each do |i|
      hamming_distance += 1 unless first[i] == second[i]
    end
    return hamming_distance
  end


end
