class Hamming

  def self.compute(start, finish)
    pairs = start.chars.zip(finish.chars)
    pairs.reject { |old,new| new.nil? || old.eql?(new) }.length
  end

end
