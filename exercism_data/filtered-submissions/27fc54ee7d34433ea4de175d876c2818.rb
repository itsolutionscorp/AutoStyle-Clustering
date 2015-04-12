# hamming.rb

class Hamming
  def compute(strand_1, strand_2)
    distance = 0
    return if strand_1.length != strand_2.length

    strand_1_ntides = strand_1.chars
    strand_2_ntides = strand_2.chars

    for i in 0..strand_1_ntides.length-1
      distance += 1 if strand_1_ntides[i] != strand_2_ntides[i]
    end
    distance

  end
end
