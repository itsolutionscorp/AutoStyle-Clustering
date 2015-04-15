def compute(a,b,difference = 0)
    if a != b
      a.chars.each_index do |i|
       if a.chars[i] != b.chars[i]
        difference += 1
       end
      end
    end
    difference
  end