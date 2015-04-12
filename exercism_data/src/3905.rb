def compute(original, other)
    shortest_length = [original.length, other.length].min

    shortest_length.times.count { |i| original[i] != other[i] }
  end