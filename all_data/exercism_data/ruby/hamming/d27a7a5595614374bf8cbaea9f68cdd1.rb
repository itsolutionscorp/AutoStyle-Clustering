class Hamming
  def self.compute a,b
    a.chars.zip(b.chars).select{|anchors| !!anchors[0] && !!anchors[1] && anchors[0] != anchors[1]}.count
  end
end
