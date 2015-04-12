class Hamming
  class << self
    def compute(strand1, strand2)
      hamming_difference = 0
      strand1.chars.each_with_index do |current_char, index|
        hamming_difference += 1 unless current_char == strand2[index]
      end
      hamming_difference
    end
  end
end
