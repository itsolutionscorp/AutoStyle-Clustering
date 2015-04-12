def compute(first, second)
    first.chars
      .zip(second.chars)
      .reject(&LEFTOVER_CHARS)
      .count(&DIFFERENT_PAIRS)
  end
end