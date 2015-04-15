class Hamming

  def self.compute(strand_a, strand_b)
    hamming_distance = 0
    strand_a.split(//).each_with_index do |character,position|
      hamming_distance += 1 if strand_a[position] != strand_b[position]
    end unless strand_a.length != strand_b.length
    hamming_distance
  end

end
