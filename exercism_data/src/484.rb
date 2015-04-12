def compute(arg1, arg2)
    (0..arg1.length).map {|char| true if arg1[char] != arg2[char] }
                    .count(true)
  end