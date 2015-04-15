class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).count do |aa, bb|
      !(aa.nil? || bb.nil? || aa == bb)
    end
  end
end
