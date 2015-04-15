class Hamming
  def self.elements_equal?(x)
    x.first == x.last
  end
  
  def self.compute (orig,compare)
    dnapairs = orig.chars.zip(compare.chars)
    dnapairs.map{|x| elements_equal? x }.count false
  end
end
