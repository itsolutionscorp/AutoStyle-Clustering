def compute(str1 = "", str2 = "")
    sequences = str1.chars.zip(str2.chars)

    sequences.keep_if { |n| n.at(0) and n.at(1) and n.uniq.length == 2 }.count
  end