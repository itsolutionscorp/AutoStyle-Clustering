class House

  def verse(num)
    "This is " + phrases(num).join(' ')
  end

  def verses(first, last)
    first.upto(last).collect do |n|
      verse(n)
    end.join("\n") + "\n"
  end

  def phrases(number)
    content[0..number-1].reverse.collect do |object, verb|
      "the #{object} that #{verb}"
    end
  end

  def content
    [
      ["house", "Jack built.\n"],
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
