class Hamming
  def self.compute(a, b)
    pair_off(a, b).inject(0) do |differences, chars|
      differences += 1 if chars[:a] != chars[:b]
      differences
    end
  end

  private

  def self.pair_off(a, b)
    a[0...b.size].chars.zip(b[0...a.size].chars).map do |chars|
      {a: chars[0], b: chars[1]}
    end
  end
end
