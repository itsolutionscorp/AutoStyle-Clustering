class Hamming
  
  def self.compute(lstrem, rsterm)
     make_pairs(lstrem, rstrem).reduce(0) do |acc,pair|
      if pair.last == nil then return acc
      else
        pair.first != pair.last ? acc+1 : acc
      end
    end
  end
  
  private
  def self.make_pairs left, right
     left.each_char.zip(right.each_char)    
  end
end
