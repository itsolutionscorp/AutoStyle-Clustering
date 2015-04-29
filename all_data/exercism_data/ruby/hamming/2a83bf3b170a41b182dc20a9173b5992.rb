class Hamming

  def self.compute(strand_1, strand_2)
    hamming = 0

    if (strand_1.is_a? String) && (strand_2.is_a? String)
      length_1 = strand_1.length
      length_2 = strand_2.length

      if length_1 > length_2
        strand_1 = strand_1.slice!(0, length_2)
      elsif length_2 > length_1
        strand_2 = strand_2.slice!(0, length_1)
      end

      strand_1.each_char.with_index do |c,i|
        hamming += 1 if c != strand_2[i]
      end
    end
    return hamming
  end
end
