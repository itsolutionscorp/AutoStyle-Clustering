# Hamming distance exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

class Hamming
  def self.compute(a, b)
    length = [a.length, b.length].min
    count = 0
    (0..(length-1)).each do |i|
      count += 1 if a[i] != b[i]
    end
    count
  end
end
