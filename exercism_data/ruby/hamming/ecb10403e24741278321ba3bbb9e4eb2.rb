class Hamming


  def self.compute(a, b)
    a.chars.map.with_index{ |c,i|1 unless (b.chars[i] == c || b.chars[i] == nil)}.compact.reduce(0, :+)
  end


end
