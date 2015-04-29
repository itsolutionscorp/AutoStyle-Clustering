class Hamming
  def self.compute(one, two)
    return 0 if one == two
    one.chars.zip(two.chars).reject{|item| item.first == item.last}.length
  end
end
