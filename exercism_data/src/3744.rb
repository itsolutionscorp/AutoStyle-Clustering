class Hamming

  def compute(sequence1, sequence2)
    chars1 = sequence1.split ''
    chars2 = sequence2.split ''
    chars1.each_with_index.reduce(0) do |acc, (char, index)|
      acc = acc + 1 if index < chars2.size and chars2[index] != char
      acc
    end

  end

end
