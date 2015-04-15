class Hamming
  def self.compute(str0, str1)
    dist = 0

    for i in 0..(str0.size)
      dist += 1 if str0[i] != str1[i]
    end

    dist
  end
end
