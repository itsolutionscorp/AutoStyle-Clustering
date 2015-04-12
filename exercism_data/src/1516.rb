class Hamming

  def compute(a,b)
    unless a.length != b.length
      c = 0
      for i in 0..(a.length-1)
        if a[i] != b[i]
          c = c + 1
        end
      end
      c
    end
  end

end
