def compute(s1, s2)
    result = 0

    # second string is shorter
    if s1.length - s2.length > 0
      shorter = s2
      longer = s1
    else
      # either the first string is shorter or it doesn't matter
      shorter = s1
      longer = s2
    end

    (0..shorter.length - 1).each do |i|
      result = result + 1 unless shorter[i] == longer[i]
    end

    result
  end