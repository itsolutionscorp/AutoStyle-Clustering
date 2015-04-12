class Hamming
  def compute(strand1, strand2)
    distance = 0
    n = [strand1.length, strand2.length].min
    strand1.chars[0, n].zip(strand2.chars).each do |a, b|
      distance += 1 if !(a.eql?b)
    end
    distance
  end
end
