class Hamming
  def self.compute(a, b)
    count  = 0
    a_enum = a.each_char
    b_enum = b.each_char

    loop do
      count += 1 if a_enum.next != b_enum.next
    end

  ensure
    return count  # We'll reach this with StopIteration
  end
end
