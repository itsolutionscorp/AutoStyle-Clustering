def compute(strand1, strand2)
    count = 0
    strand1.length.times do |index|
      count += 1 if strand1[index] != strand2[index]
    end

    count
  end