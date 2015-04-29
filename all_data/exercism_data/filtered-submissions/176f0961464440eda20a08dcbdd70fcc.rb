def compute a, b
     (0 .. [a.length-1, b.length-1].min).
         map {|i| a[i]==b[i] ? 0 : 1}.
         reduce :+
  end