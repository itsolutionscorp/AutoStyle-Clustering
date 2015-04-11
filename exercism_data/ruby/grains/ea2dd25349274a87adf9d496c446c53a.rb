class Grains 
  def self.create_hash(count)
    output = {1 => 1}
    (2..count).each do |i|
      output[i] = output[i-1] * 2
    end
    output
  end
  
  CHESS_GRAINS = Grains.create_hash(64) 
  
  def square(number)
    CHESS_GRAINS[number]
  end
  
  def total
    CHESS_GRAINS.values.reduce(:+)
  end
end
