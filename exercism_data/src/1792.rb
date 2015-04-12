def compute(entry1, entry2)
    return 0 if entry1 == entry2
    len = [entry1.length, entry2.length].min
    diff = 0
    i = 0
    while i < len
      diff += 1 if entry1[i] != entry2[i]
      i += 1
    end
    return diff
  end