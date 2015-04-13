def compute(strand_a, strand_b)
    distance = 0

    length = [strand_a, strand_b].sort { |a, b| a.length <=> b.length }
             .first.length

    length.times do |index|
      if strand_a[index] != strand_b[index]
        distance += 1
      end
    end

    distance
  end