# Class for computing Hamming Distance
class Hamming
  def compute(str1, str2)
    str1.split('').zip(str2.split('')).reduce(0) { |a, e|
      e[0] == e[1] || e[1].nil? ? a : a + 1
    }
  end
end
