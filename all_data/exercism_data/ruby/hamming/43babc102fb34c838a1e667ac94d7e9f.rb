class Hamming

  def self.compute(source, destination)
    distance    = 0
    source      = source.split('')
    destination = destination.split('')
    
    source.zip(destination).each do |src, dst|
      distance += 1 unless src == dst 
    end
    
    distance
  end

end
