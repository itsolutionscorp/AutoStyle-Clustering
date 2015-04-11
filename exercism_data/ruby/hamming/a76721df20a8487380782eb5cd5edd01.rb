class Hamming
  def self.compute(first,second)
    first.chars.zip(second.chars).count{ |a,b| a != b && a && b}
  end
end
