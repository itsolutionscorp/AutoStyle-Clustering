class Hamming
  def self.compute(dna_one, dna_two, count = 0)
    short = dna_one.length <= dna_two.length ? dna_one : dna_two
    long  = short == dna_one ? dna_two : dna_one

    short.each_char.with_index(1) { |char, i| count += 1 if char != long[i-1] }

    count
  end
end
