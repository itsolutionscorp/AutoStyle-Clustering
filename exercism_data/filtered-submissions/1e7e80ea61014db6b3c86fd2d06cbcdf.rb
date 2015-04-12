module Hamming
  def compute(strand_1, strand_2)
    size1, size2 = strand_1.size, strand_2.size
    if size1 > size2
      strand_1 = strand_1[0..size2 - 1]
    elsif size2 > size1
      strand_2 = strand_2[0..size1 - 1]
    end

    distance = 0
    strand_1 = strand_1.split('').each
    strand_2 = strand_2.split('').each

    loop do
      distance += 1 unless strand_1.next == strand_2.next
    end

    distance
  end
end
