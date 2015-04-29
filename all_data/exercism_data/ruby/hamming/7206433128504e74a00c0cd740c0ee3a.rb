class Hamming

  def self.compute(a, b)
    a.each_char.each_with_index.inject(0) do |hamming, (value, index)|
      hamming += 1 unless value == b[index]
      hamming
    end
  end

end
