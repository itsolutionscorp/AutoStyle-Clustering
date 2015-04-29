class Hamming
  def self.compute(dna_left, dna_right)

    left, right = convert_to_arrays(dna_left, dna_right)

    distance = 0

    left.zip(right) do |base_left, base_right|
      break if base_right.nil?
      distance += 1 unless base_left == base_right
    end

    distance
  end

  private

  def self.convert_to_arrays(a, b)
    [a.split(''), b.split('')]
  end

end
