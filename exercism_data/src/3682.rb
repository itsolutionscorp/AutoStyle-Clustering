class Hamming
  def compute(a, b)
    matches = 0
    a.chars.each_with_index do |ac, i|
      unless ac == b.chars[i]
        matches += 1
      end
    end
    matches
  end
end
