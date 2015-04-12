class Hamming
  def compute(strand1, strand2)
    strand1.split('').each_with_index.reduce(0) do |count, (char, index)|
      count += 1 if strand2[index] != char
      count
    end
  end
end
