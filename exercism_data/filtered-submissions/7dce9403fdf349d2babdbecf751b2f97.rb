def compute(first, second)
    first.each_char.each_with_index.inject(0) do |sum, (nucleotide, index)|
      break sum if index >= second.size
      nucleotide == second[index] ? sum : sum + 1
    end
  end