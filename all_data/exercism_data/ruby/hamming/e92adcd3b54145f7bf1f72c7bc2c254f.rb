class Hamming
  def self.compute(expected, mutation)
    counter = 0
    if expected.length >= mutation.length
      longest = expected
      shortest = mutation
    else
      longest = mutation
      shortest = expected
    end

    (0...shortest.length).each do |index|
      if shortest[index] != longest[index]
        counter += 1
      end
    end
    return counter
  end
end
