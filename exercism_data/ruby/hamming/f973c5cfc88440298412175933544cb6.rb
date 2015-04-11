class Hamming
  def self.compute dna_a, dna_b
    differences = 0

    point_mutations_between(dna_a, dna_b) do
      differences += 1
    end

    differences
  end

  def self.point_mutations_between(dna_a, dna_b)
    index = 0
    dna_a.each_char do |char|
      if !dna_b[index].nil?
        char != dna_b[index] && yield
      end
      index += 1
    end
  end

end
