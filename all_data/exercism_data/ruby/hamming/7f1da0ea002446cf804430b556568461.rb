class Hamming
  def self.compute(a,b)
    n = [a, b].map(&:length).min
    n.times.reduce(0) do |score, i|
      score + (a[i]==b[i] ? 0 : 1)
    end
  end
end
