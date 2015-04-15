class Hamming

  def self.compute(start, finish)
    pairs = start.chars.zip(finish.chars)
    pairs.reject { |old,new| old == new }.length
  end

end
