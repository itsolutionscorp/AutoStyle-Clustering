def compute ancestor, new
    distance = 0
    length = [ancestor.length, new.length].min
    (0..(length - 1)).each do |index|
      distance += 1 if ancestor[index] != new[index]
    end
    distance
  end