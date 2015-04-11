class Hamming
  def self.compute dna_a, dna_b
    a_array = dna_a.split ''
    b_array = dna_b.split ''

    counter = 0
    pairs = a_array.zip b_array
    pairs.each do |a, b|
      break if !(a && b)
      counter += 1 if a != b
    end
    counter
  end
end
