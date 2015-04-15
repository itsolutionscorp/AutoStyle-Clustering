class Hamming

  def self.compute(a,b)

    a.size > b.size ? length = b.size - 1: length = a.size - 1

    difference = 0

    (0..length).map { |i| difference += 1 if a[i] != b[i] }

    difference

  end

end
