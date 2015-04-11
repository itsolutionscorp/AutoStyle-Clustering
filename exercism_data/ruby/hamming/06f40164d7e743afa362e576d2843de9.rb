class Hamming
  def self.compute(first, last)
    trim(first, last).transpose.count { |a,b| a != b }
  end

  def self.trim(first, last)
    [first.chars.first(last.size), last.chars.first(first.size)]
  end
end
