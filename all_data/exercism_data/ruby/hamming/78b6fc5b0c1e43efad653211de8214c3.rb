class Hamming
	
  def self.compute(x, y)
  	distance = 0
    x.split("").each_with_index do |letter, index|
      distance += 1 if letter != y.split("")[index]
    end
    distance 
  end

end
