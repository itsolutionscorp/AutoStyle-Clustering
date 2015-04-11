class Hamming
  def self.compute a, b
    a.each_char.with_index.select do |val,i|
      b[i].nil? ? false : val != b[i]
    end.length
  end
end
