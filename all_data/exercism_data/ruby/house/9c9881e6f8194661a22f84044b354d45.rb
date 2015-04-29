class House

  def verse(i)
    "This is " + phrases(i).join(' ') + "\n"
  end

  def verses(first, last)
    first.upto(last).collect do |i|
      verse(i)
    end.join("\n") + "\n"
  end

  def phrases(i)
    content.take(i).reverse.collect do |object, verb|
      "the #{object} that #{verb}"
    end
  end

  def content
    [
      ["house", "Jack built."],
      ["malt", "lay in"],
      ["rat", "ate"],
      ["cat", "killed"],
      ["dog", "worried"],
      ["cow with the crumpled horn", "tossed"],
      ["maiden all forlorn", "milked"],
      ["man all tattered and torn", "kissed"],
      ["priest all shaven and shorn", "married"],
      ["rooster that crowed in the morn", "woke"],
      ["farmer sowing his corn", "kept"],
      ["horse and the hound and the horn", "belonged to"]
    ]
  end

end
