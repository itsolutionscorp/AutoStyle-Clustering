class Hamming
  def compute(a, b)
    a.split('').zip(b.split('')).map do |pair|
      next 0 if pair[0].nil? || pair[1].nil?
      next 0 if pair[0] == pair[1]
      1
    end.reduce(&:+)
  end
end
