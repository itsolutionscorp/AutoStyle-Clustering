class Hamming
  def self.compute (str1, str2)
    str1.chars.zip(str2.chars).map {|pair| pair[0] <=> pair[1]}.map {|n| n.abs}.inject(0) {|sum, n| sum += n}
  end    
end
