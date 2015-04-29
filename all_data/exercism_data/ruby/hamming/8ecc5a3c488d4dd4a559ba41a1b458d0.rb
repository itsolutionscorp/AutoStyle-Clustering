class Hamming
  def self.compute(d1, d2)
    (0 .. [d1.length, d2.length].max).select { |idx|
      if (d1[idx].nil? or d2[idx].nil?)
        false
      else
        d1[idx] != d2[idx]
      end
    }.count
  end
end
