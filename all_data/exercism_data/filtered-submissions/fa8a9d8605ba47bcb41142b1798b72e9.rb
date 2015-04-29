def compute(a,b)
    n = [a, b].map(&:length).min
    (0...n).reduce(0) do |score, i|
      score + (a[i]==b[i] ? 0 : 1)
    end
  end