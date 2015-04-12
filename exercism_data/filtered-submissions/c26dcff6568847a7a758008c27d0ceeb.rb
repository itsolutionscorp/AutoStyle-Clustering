class Hamming
  def compute(a, b)
    a,b = b,a if b.length < a.length
    a.chars.zip(b.chars).inject(0) do |distance, c|
      c[0] == c[1] ? distance : distance + 1
    end
  end
end
