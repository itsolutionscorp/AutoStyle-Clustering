class Hamming
  def self.compute(strand1, strand2)
    difference = 0

    strand1.each_char.with_index do |char, index|
      difference += 1 if strand2[index] != char
    end

    difference
  end
end
