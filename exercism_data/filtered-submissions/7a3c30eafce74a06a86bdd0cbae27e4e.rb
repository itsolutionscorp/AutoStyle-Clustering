def compute(strand1, strand2)

    min_length = [strand1.length,strand2.length].min
    (0...min_length).count { |n| strand1[n] != strand2[n]}

  end

end