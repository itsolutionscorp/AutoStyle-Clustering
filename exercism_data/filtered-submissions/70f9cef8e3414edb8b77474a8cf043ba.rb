class Hamming
  def compute(ntide_1, ntide_2)
    count = 0

    0.upto(ntide_1.length) do |i|
      count += 1 if ntide_1[i] != ntide_2[i]
    end

    return count
  end
end
