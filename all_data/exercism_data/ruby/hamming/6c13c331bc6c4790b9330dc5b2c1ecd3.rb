module Hamming
  def self.compute(a, b)
    count = 0
    a.split("").zip(b.split("")).each { |a, b| count+= 1 unless a.eql?(b) }
    count
  end
end
