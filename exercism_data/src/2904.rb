def compute(source, destination)
    distance = 0
    source = source.split('')
    destination = destination.split('')
    
    source.zip(destination).each do |src, dst|
      if src != dst 
        distance += 1
      end
    end
    
    distance
  end