def compute(a,b)
    # Possible quick win
    return 0 if a == b

    # running total for the hamming distance. Start off assuming none then add to it
    h  = 0

    [a.length,b.length].min.times do |i|
      # Count the differences
      if a[i] != b[i]
        h += 1
      else
        next
      end
    end

    return h
  end
end