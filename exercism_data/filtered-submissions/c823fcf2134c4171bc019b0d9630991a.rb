class Hamming
  def compute(strand1, strand2)
    differences = 0

    s1_size = strand1.length
    s2_size = strand2.length

    size = s1_size < s2_size ? s1_size : s2_size


    (0...size).each do |i|
      if strand1[i] != strand2[i]
        differences += 1
      end
    end

    differences
  end
end
