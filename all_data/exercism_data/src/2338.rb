def compute(a,b)
   if a == b
     0
   else
     b = b.chars
     l = a.length < b.length ? a.length : b.length
     a.chars.take(l).map.with_index {|v,i| v != b[i] ? 1 : 0}.inject {|sum,x| sum + x}
   end
  end