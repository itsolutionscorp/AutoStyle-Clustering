class Hamming

  def self.compute(a, b)
    difference = 0
    index = 0
    a.each_char do |char|
      if char != b[index]
        difference += 1
      end
      index += 1
    end
    difference
  end

end
