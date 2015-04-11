class Hamming
  def self.compute(dna_left, dna_right)
    left, right = convert_to_arrays(dna_left, dna_right)
    distance = 0
    left.zip(right) do |arr|
      break if arr[1].nil?
      distance += 1 unless arr[0] == arr[1]
    end
    distance
  end

  private

  def self.convert_to_arrays(a, b)
    [a.split(''), b.split('')]
  end

end
