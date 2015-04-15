def compute(p1, p2)
    p1.each_char.with_index do |c, i|
      c =+ 1 if c != p2.slice(i)
    end
  end