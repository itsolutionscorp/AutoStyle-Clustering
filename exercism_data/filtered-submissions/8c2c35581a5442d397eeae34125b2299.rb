class Hamming
  def compute(strand1, strand2)
    comparison_array = strand1.chars.zip(strand2.chars)
    comparison_array.reduce(0) do |distance, comparison_pair|
      comparison_pair.first != comparison_pair.last ? distance += 1 : distance
    end
  end
end
