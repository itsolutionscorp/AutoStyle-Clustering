def compute(source, mutation)
    distance = 0
    min_length = [source.length, mutation.length].min - 1

    (0..min_length).each do |index|
      source_acid = source[index]
      mutation_acid = mutation[index]
      distance = distance + 1 unless source_acid == mutation_acid
    end
    
    distance
  end
end