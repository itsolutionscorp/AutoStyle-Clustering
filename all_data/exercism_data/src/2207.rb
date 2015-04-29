def compute(source, destination)
    distance    = 0

    if source.size == destination.size
      source.chars.zip(destination.chars).each do |src, dst|
        distance += 1 unless src == dst 
      end
    end    
    
    distance
  end