class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).count do |pair|
      !(pair[0].nil? || pair[1].nil? || pair[0] == pair[1])
    end
  end
end
