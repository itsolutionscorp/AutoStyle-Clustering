class Hamming
  def self.compute(strand1,strand2)
    distance = 0
    strand1.split("").each_with_index do |char,i|
      if char != strand2[i] then
        distance += 1
      end
    end
    return distance
  end
end
