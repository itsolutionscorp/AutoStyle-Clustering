class Hamming
  def compute(first, second)
    # Store both strands in separate arrays
    first = first.chars.to_a
    second = second.chars.to_a
    # Compare both arrays and return the difference
    difference = 0
    increment = 0
    first.each do |f|
      s = second[increment]
      increment += 1
      if f != s
        difference += 1
      else
      end
    end
    return difference
  end
end
