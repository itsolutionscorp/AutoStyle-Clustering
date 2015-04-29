class Hamming

  def self.compute(a,b)
    a.chars.each_with_index.select { |item, index| item != b.chars[index] unless !b.chars[index] }.count
  end

end
