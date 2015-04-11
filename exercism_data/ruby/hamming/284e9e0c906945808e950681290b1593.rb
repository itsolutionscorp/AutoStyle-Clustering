class Hamming
  def self.compute(one, two)
    return 0 if one.length != two.length

    recurse one, two, 0
  end

  def self.recurse(one, two, i)
    return 0 if i > two.length

    return recurse(one, two, i+1) + (one[i] != two[i] ? 1 : 0)
  end
end
