def compute(first, second)
    first.chars.zip(second.chars).map { |pair|
      pair.first && pair.last && pair.first != pair.last
    }.count { |match| match }
  end