class Hamming
  def compute a, b
    a.split("").zip(b.split("")).count {|elem| elem[0] != elem[1] and !elem[1].nil?}
  end
end
