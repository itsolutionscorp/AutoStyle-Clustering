class Hamming

  def compute(strand1, strand2)

    max_index = [strand1.length, strand2.length].min

    max_index.times.count do |i|
      strand1[i] != strand2[i]
    end
  end
end
