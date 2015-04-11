module Hamming
  def self.computex(a, b)
    shorter_length = [a.length, b.length].min - 1
    diff = 0
    (0..shorter_length).each do |n|
      diff += 1 if a[n] != b[n]
    end
    diff
  end

  def self.compute(a,b)
    diff = 0
    [a.length, b.length].min.times do |i|
      diff += 1 if a[i] != b[i]
    end
    diff
  end

  # def self.compute(a,b)
  #   "ABCDE"
  #   "ABECD"
  #
  #   [["A","A"], ["B","B"],[]].map{|pair| pair.first == pair.last }
  #
  #   [true, false, true,...].
  # end
end
