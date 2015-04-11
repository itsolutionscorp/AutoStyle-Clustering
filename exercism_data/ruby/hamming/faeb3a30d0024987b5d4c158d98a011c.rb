class Hamming

  def self.compute(a, b)
    c = [a.chars, b.chars].transpose
    c.inject(0) do |sum, set| 
      sum + (set.first == set.last ? 0 : 1)
    end
  end

end
