class Hamming
  class << self
    def compute(dna1, dna2)
      str1, str2 = order_strs(dna1, dna2)
      (0..(str1.length - 1)).inject([]) do |diff_chars, ind|
        diff_chars << str1[ind] unless str1[ind] == str2[ind]
        diff_chars
      end.count
    end

    private

    def order_strs(dna1, dna2)
      dna1.length <= dna2.length ? [dna1, dna2] : [dna2, dna1]
    end
  end
end
