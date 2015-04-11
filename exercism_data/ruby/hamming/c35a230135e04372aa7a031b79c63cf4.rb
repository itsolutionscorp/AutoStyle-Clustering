class Hamming
  def self.compute(str_1, str_2)
    # assume str_1 and str_2 are equal length
    str_1.length.times.inject(0) do |dist, index|
      str_1[index] != str_2[index] ? dist += 1 : dist
    end
  end
end
