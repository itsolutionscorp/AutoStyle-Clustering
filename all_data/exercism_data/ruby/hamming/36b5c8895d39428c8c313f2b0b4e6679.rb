class Hamming
  def self.compute(one, two)
    distance = 0
    one.split("").each_with_index do |item, i| 
      if (item != two[i]) then
        distance += 1
      end 
    end
    return distance
  end
end
