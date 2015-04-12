class Hamming
  def compute(a, b)

    min = [a.to_s.length, b.to_s.length].min

    (0...min).count do |i|
      a[i] != b[i]
    end

  end
end
