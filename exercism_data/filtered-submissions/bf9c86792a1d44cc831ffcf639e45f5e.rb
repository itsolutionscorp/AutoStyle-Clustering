def compute(a, b)
    ## set initial distance
    distance = 0

    ## Iterate for the length of the longer strand
    (0..[a.length, b.length].max).each do |i| 
      next if i + 1 > a.length or i + 1 > b.length
      distance += 1 unless a[i] == b[i]
    end

    distance
  end
end