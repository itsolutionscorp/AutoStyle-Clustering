class Hamming

  def compute(strand1, strand2)
    strand1 = strand1.split("")
    strand2 = strand2.split("")

    distance = 0

    strand1.each_with_index do |n, i|
      distance += 1 if n != strand2[i]
    end

    return distance
  end
end
