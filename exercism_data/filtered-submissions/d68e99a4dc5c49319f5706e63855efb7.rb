class Hamming
  class << self
    def compute(strand_1, strand_2)
      count = 0
      strand_1.chars.each_with_index do | char, i |
        count += 1 if char != strand_2[i] unless strand_2[i].nil?
      end
      count
    end
  end
end
