class Hamming
  def compute x, y
    size = [x.size, y.size].min
    x.chars.first(size).zip(y.chars.first(size)).count {|a,b| a != b}
  end

end
