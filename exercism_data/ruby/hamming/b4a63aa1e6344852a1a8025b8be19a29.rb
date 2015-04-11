class Hamming
  def self.compute(a,b)
    limit = [a.size,b.size].min
    a,b = a.chars.take(limit),b.chars.take(limit)
    a.each_with_index.count do |letter,index|
      letter != b[index]
    end
  end
end
