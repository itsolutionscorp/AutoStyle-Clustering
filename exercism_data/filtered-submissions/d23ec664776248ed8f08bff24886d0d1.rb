module Hamming
  def compute(a,b)
    distance = 0

    short, long = [a, b].sort
    
    long.chars[0..(short.length - 1)].zip(short.chars).each do |ac, bc|
      distance += 1 if ac != bc
    end
    
    distance
  end
end
