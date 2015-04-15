# hamming.rb
class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).reject { |c| c.first == c.last  }.size
  end
end
