def compute(original, mutated)
    difference = 0
    original.chars.each_with_index do |orig_chr, index|
      break unless mutated[index]
      difference += 1 if orig_chr != mutated[index]
    end
    difference
  end
end