class Hamming
  def compute(strand1, strand2)
    strand1.split('').zip(strand2.split(''))
          .map { |base1, base2| base1 != base2 }
          .count { |mutated| mutated == true }
  end
end
