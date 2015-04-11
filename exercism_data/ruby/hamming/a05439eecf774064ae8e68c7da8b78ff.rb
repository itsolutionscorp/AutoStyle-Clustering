class Hamming
  def self.compute(a, b)
    return 0 if a == b
    shortestlength = [a, b].min.length - 1

    hamming = 0
    (0..shortestlength).each do |nt|
      hamming += 1 if a[nt] != b[nt]
    end
    hamming
  end
end
