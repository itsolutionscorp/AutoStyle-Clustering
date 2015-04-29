class Hamming
  def self.compute(a, b)
    a.length - a.intersection(b)
  end
end

class String
  def intersection(other)
    str = self.dup
    sum = 0

    other.chars.each_with_index do | char, index |
      sum += 1 if char == str[index]
    end

    sum
  end
end
