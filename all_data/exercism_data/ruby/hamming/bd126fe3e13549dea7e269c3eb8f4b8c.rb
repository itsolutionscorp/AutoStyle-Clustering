class Hamming

  def self.compute(a, b)
    a.each_char.each_with_index.count do |a_char, i|
      b[i] && a_char != b[i]
    end
  end
end
