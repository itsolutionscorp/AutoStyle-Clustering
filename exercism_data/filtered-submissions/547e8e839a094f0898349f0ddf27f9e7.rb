class Hamming
  def compute(a, b)
    hamming = 0

    (0...a.length).each do |i|
      hamming += 1 if a[i] != b[i]
    end

    hamming
  end
end