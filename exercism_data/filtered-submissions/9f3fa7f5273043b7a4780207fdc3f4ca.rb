class Hamming

  class << self

    def compute(dna1, dna2)
      s1 = dna1.split(//)
      s2 = dna2.split(//)

      acc = 0
      s1.each_with_index do |v,i|
        acc += 1 if v != s2[i]
      end
      return acc
    end

  end

end
