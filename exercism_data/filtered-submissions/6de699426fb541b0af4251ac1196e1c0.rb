class Hamming
  def compute(strand1, strand2)
    diff = 0
    strand1 = strand1.split("")
    strand2 = strand2.split("")

    if strand1.length < strand2.length
      length = strand1.length - 1
    else
      length = strand2.length - 1
    end

      (0..length).each do |i|
        if strand1[i] != strand2[i]
          diff += 1
        end
      end

    diff
  end
end
