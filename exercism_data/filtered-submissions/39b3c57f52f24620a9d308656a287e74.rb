class Hamming

  def compute(a, b)
    difference = 0
    (0...[a.size, b.size].min).each do |char|
      difference += 1 unless a[char] == b[char]
    end
    difference
  end

end
