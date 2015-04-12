class Hamming
  def compute(strand1, strand2)
    count = 0
    strand1 = strand1.split("")
    strand2 = strand2.split("")
    strand1.each_with_index do |char, index|
      if strand1[index] != strand2[index]
        count += 1
      end
    end
    count
  end
end
