def compute(strand1, strand2)
    distance = 0
    # Trivial case
    if strand1 == strand2
      return distance
    end
    # Determine smallest string
    max = strand1.length > strand2.length ? strand2.length : strand1.length
    # Compare strings and update distance
    for i in 0..max - 1
      # Is each character in string equal? Add 0 else 1.
      distance += strand1[i] == strand2[i] ? 0 : 1;
    end
    return distance
  end