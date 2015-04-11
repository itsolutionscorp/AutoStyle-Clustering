class Hamming

  def self.compute(a, b)
    ac = a.chars
    bc = b.chars
    ac, bc = bc, ac if a.length > b.length # make a the shorter one
    sum = 0
    #ac.each_index { |i| sum += 1 if ac[i] != bc[i] }
    ac.zip(bc) { |x, y| sum += 1 if x != y }
    sum
  end
end
