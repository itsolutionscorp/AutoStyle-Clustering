def compute(first, second)
    first.slice(0, second.size).chars.each_with_index.select do |(nucleotide, index)|
      nucleotide != second[index]
    end.count
  end