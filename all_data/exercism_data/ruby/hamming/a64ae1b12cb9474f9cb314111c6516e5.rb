class Hamming
  def self.compute (a,b)
    a.chars.reject.with_index{|x,y| x==b.chars[y]}.count
  end
end
