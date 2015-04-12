class Hamming

  def compute(strain_a, strain_b)
    result = 0
    if strain_a.size == strain_b.size or strain_a.size < strain_b.size
      (0..strain_a.size - 1).each do |i|
        if strain_a[i] != strain_b[i]
          result += 1
        end
      end
    elsif strain_b.size < strain_a.size
      (0..strain_b.size - 1).each do |i|
        if strain_a[i] != strain_b[i]
          result += 1
        end
      end
    end
    result
  end
end
