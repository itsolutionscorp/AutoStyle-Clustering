class Hamming
  def self.compute(a, b)
    a.chars.select.with_index do |char, i|
      char != b[i]
    end.count
  end
end