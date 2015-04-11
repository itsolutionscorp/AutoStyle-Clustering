class Hamming
  def self.compute(a, b)
    first, second = [a, b].sort { |x, y| x.length <=> y.length }
    return first.split('').each_with_index.inject(0) do |r, (e, index)|
      e == second[index] ? r : r + 1
    end
  end
end
