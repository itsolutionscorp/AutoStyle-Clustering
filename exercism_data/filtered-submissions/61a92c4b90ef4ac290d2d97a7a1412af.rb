def compute(let1, let2)
    let1.chars.zip(let2.chars)
    .select { |char1, char2| char1 != char2 }
    .length
  end