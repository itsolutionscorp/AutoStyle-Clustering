def compute(strand, other)
    strand
      .chars
      .slice(0, other.size)
      .zip(other.chars)
      .count { |x, y| x != y }
  end
end