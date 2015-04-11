class Hamming
  def self.compute(s1, s2)
    data = s1.split('').zip s2.split('')
    data.collect { |v1, v2| v1 == v2 }.count(false)
  end
end
