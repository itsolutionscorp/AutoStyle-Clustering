class Hamming

  def self.compute(start, finish)
    pairs = start.chars.zip(finish.chars)
    pairs.count { |old,new| new && old != new }
  end

end
