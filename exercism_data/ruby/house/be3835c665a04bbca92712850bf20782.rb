class House
  def verse(n)
    pieces = [
      ["horse and the hound and the horn", "belonged to the",],
      ["farmer sowing his corn",           "kept the"],
      ["rooster that crowed in the morn",  "woke the"],
      ["priest all shaven and shorn",      "married the"],
      ["man all tattered and torn",        "kissed the"],
      ["maiden all forlorn",               "milked the"],
      ["cow with the crumpled horn",       "tossed the"],
      ["dog",                              "worried the"],
      ["cat",                              "killed the"],
      ["rat",                              "ate the"],
      ["malt",                             "lay in the"],
      ["house",                            "Jack built."],
    ]

    "This is the #{
      pieces[pieces.size-n, n]
        .map { |noun1, verb1| "#{noun1} that #{verb1}" }
        .join(" ")
    }\n"
  end

  def verses(offset, length)
    length.times.map { |n| "#{verse offset+n}\n" }.join("")
  end
end
