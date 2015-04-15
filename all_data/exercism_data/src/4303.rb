class Hamming
  def compute(first, second)
    pairs = first.codepoints.zip(second.codepoints)

    pairs.reduce(0) do |acc, pair|
      x, y = pair

      if x == y
        acc
      else
        acc + 1
      end
    end
  end
end
