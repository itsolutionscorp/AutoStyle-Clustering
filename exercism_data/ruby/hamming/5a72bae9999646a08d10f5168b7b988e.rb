class Hamming
  def self.compute(a, b)
    combined = combine a, b
    delta = map combined
    reduce delta
  end

  def self.combine(a, b)
    a.chars.zip b.chars
  end

  def self.map(a)
    a.map{|bases| compare(*bases) }
  end

  def self.reduce(a)
    a.reduce(&:+) || 0
  end

  def self.compare(a, b)
    (a == b || a.nil? || b.nil?) ? 0 : 1
  end
end
