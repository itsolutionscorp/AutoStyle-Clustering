module Hamming
  def compute(*args)
    hamming_distance(collate(*small_large(*args)))
  end

  private

  def small_large(*args)
    args.sort { |one, two | one.size <=> two.size }
  end

  def collate(a, b)
    a.chars.zip(b.chars).select { |_, bottom| !bottom.nil? }
  end

  def hamming_distance(pairs)
    pairs.select { |top, bottom| top != bottom }.size
  end
end
Hamming.extend(Hamming)
