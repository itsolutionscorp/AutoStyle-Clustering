class Hamming
  def compute(a, b)
    a = a.chars
    b = b.chars

    hamming = 0
    a.each_index do |index|
      break unless b[index]
      hamming += 1 unless a[index] == b[index]
    end
    hamming
  end
end
