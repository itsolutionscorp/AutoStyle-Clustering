def compute(strand1, strand2)    
    s1 = strand1.chars
    s2 = strand2.chars
    count = 0
    loop do
      count += 1 if s1.next != s2.next
    end
    return count
  end