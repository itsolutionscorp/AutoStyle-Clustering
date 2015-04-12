class Hamming
  def compute(strand1, strand2)
    count = 0
    compare_length = strand1.length < strand2.length ? strand1.length : strand2.length

    compare_length.times do |i|
      count = count + 1 unless strand1[i] == strand2[i]
    end

    return count
  end
end
