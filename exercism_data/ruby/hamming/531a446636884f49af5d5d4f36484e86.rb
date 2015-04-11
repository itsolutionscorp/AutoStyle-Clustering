class Hamming
  def self.compute(dna_1, dna_2)
    count = 0

    dna_1.each_char.with_index do |char, index|
      other_char = dna_2[index]

      next unless other_char

      unless char == other_char
        count += 1
      end
    end

    count
  end
end
