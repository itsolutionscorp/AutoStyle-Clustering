def compute first, second
    shortest = [first.length, second.length].min
    shortest.times.count{ |i| first[i] != second[i] }
  end