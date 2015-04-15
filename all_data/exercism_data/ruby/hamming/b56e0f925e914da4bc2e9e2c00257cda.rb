class Hamming
  def self.compute(first, second)
    difference_count = 0

    a, b = Hamming.equalize(first, second)

    a.chars.each_with_index do |char, index|
      difference_count += 1 if char != b[index]
    end
    difference_count
  end

  def self.equalize(a, b)
    a = a[0..b.length-1] if a.length > b.length
    b = b[0..a.length-1] if b.length > a.length
    return a, b
  end
end
