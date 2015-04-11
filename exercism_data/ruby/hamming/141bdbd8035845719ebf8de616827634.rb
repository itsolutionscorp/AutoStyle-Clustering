class Hamming

  def self.compute(string_1, string_2)
    distance = 0
    chars_1 = string_1.split('')
    chars_2 = string_2.split('')

    if chars_1.count == chars_2.count
      chars_1.each_with_index do |nucleotide, indx|
        distance += 1 if nucleotide != chars_2[indx]
      end
    end

    distance
  end

end
