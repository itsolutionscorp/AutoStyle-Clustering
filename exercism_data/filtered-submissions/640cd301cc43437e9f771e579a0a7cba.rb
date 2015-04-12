class Hamming
  class << self
    def compute(strand1, strand2)
      difference = 0
      strand1.chars.each_with_index do |_, offset|
        difference += 1 unless strand1[offset] == strand2[offset]
      end
      difference
    end
  end
end
