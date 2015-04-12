def compute(strand1, strand2)
    count = 0
    loop do
      if strand1.next != strand2.next
        count += 1
      end
    end
    return count
  end