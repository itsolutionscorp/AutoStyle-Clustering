class Hamming
  def self.compute x, y
    size = [x.size, y.size].min
    x.chars.first(size).zip(y.chars.first(size)).count {|a,b| a != b}
  end

end
