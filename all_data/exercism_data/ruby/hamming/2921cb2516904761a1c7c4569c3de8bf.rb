class Hamming

  def self.compute(string1, string2)
    return 0 if string1 == string2

    comparison = string1.chars.zip(string2.chars).map {|x,y| x==y}
    return comparison.count(false)
  end
end
