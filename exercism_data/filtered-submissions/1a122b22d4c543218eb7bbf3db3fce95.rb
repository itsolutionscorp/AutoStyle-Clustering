class Hamming
  def compute(strand1, strand2)
    strand1 = strand1.split('')
    strand2 = strand2.split('')

    distance = 0
    strand1.each_with_index do |nuc, index|
      return 0 if strand2[index] == nil
      distance += 1 if nuc != strand2[index]
    end
    distance
  end
end
