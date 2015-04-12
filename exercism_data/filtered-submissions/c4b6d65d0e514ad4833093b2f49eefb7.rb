class Hamming

  def compute(strand1, strand2)
    sum = 0
    strand1_enum = strand1.chars.each
    strand2_enum = strand2.chars.each

    loop do
      # StopIteration raised when enumerator ends.
      sum += 1 unless strand2_enum.next == strand1_enum.next
    end

    sum
  end
end
