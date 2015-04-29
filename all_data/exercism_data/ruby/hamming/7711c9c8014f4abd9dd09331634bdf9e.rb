class Hamming
  def self.compute(a, b)
    a_len = a.length
    b_len = b.length
    hamming = 0
    common_len = a_len > b_len ? b_len : a_len
    common_len -= 1

    for i in 0..common_len do
      hamming += 1 if a[i] != b[i]
    end

    return hamming
  end
end
