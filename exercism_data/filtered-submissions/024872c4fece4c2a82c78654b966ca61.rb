class Hamming
  def compute(a, b)
    return -1 if a.length != b.length
    
    count = 0
    a.length.downto(1) do |i|
      if a[i-1] != b[i-1]
        count += 1
      end
    end
    count

  end
end
