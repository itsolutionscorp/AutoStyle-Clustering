class Hamming
  def compute(strand1, strand2)
    count = 0
    loop do
      count += 1 if strand1.next != strand2.next || break
    end
    return count
  end
end
