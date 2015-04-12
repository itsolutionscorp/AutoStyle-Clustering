def compute(entry1, entry2)
    return 0 if entry1 == entry2
    entry1.length > entry2.length ? (len = entry2.length) : (len = entry1.length)
    diff = 0
    entry1 = entry1.split("")
    entry2 = entry2.split("")
    i = 0
    while i < len
      diff += 1 if entry1[i] != entry2[i]
      i += 1
    end
    return diff
  end