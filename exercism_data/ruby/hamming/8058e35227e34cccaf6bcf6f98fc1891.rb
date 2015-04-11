# hamming.rb
class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).count { |c, d| c != d  }
  end
end
