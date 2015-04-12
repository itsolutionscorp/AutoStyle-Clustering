def compute(strand1, strand2)
    i = 0
    total = 0
    strand1.each_char do |c|
      if strand2[i] != nil
        total += 1 if c != strand2[i]
        i += 1
      end
    end
    return total