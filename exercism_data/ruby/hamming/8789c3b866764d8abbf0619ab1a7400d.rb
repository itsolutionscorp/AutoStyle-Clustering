class Hamming
  def self.compute(first, second)
    first.chars.zip(second.chars).take(second.length).count {|x,y| x != y }
  end
end
