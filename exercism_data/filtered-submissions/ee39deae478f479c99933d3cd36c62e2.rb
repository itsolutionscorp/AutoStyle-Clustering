class Hamming
  def compute a,b
    a.chars.zip(b.chars).count{|anchors| !(anchors[0] <=> anchors[1]).to_i.zero?}
  end
end
