def compute a,b
    a.chars.zip(b.chars).select{|anchors| !(anchors[0] <=> anchors[1]).to_i.zero?}.count
  end