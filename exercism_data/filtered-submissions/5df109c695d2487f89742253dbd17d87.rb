class Hamming
  def compute a, b
    [a, b].min_by{|x| x.size}.size.times.count {|i| a[i] != b[i]}
  end
end
