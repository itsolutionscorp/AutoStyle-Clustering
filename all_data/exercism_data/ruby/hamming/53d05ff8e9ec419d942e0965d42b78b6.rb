class Hamming
  def self.compute(one, two, i = nil)
    return 0 if one.length != two.length
    i = (i.nil? ? 0 : i + 1)
    return 0 if i > two.length 
    compute(one, two, i) + (one[i] != two[i] ? 1 : 0)
  end
end
