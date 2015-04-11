class House
  def verse(n)
    output = "This is"
    (n - 2).downto(0) do |i|
      noun, verb = COMPONENTS[i]
      output << " the #{noun} that #{verb}"
    end
    output + " the house that Jack built.\n"
  end

  def verses(first, last)
    first.upto(last).map { |i| verse(i) }.join("\n") + "\n"
  end

  COMPONENTS = [
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
