def compute(strand1, strand2)
    return if (strand1.nil? or strand2.nil?)

    result   = 0
    shortest = [strand1, strand2].min_by(&:length)

    shortest.length.times do |index|
      if (strand1[index] != strand2[index])
        result += 1
      end
    end

    result
  end