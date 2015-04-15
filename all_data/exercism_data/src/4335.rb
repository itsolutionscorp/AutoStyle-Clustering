class Hamming
  def compute(strand1, strand2)
    count = 0
    strand1.split('').each_with_index do |char, index|
      if strand2[index] != char
        count += 1
      end
    end
    count
  end
end
