class Hamming
  def compute a, b
     (0 ... [a.length, b.length].min).
         map {|i| a[i]==b[i] ? 0 : 1}.
         reduce :+
  end 
end
