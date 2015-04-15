class Hamming
  def self.compute(one, two)
    one.chars.map.with_index { |letter,index| letter==two[index] }
       .select { |is_match| is_match===false }.length
  end
end
