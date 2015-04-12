class Hamming

  def compute(a, b)
    length = [a.length, b.length].min
    c = [a[0, length].chars, b[0, length].chars].transpose
    c.inject(0) do |sum, set| 
      sum + (set.first == set.last ? 0 : 1)
    end
  end

end
