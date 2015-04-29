class House

  def verse(number)
    body = (number - 2).downto(0).inject("") do |previous, count|
      "#{previous}the #{VERSES[count].first} that #{VERSES[count].last} "
    end

    "This is #{body}the house that Jack built.\n"
  end

  def verses(first, last)
    (first..last).inject("") { |result, count| "#{result}#{verse count}\n" }
  end

  VERSES = [["malt", "lay in"], ["rat", "ate"], ["cat", "killed"], ["dog", "worried"],
            ["cow with the crumpled horn", "tossed"], ["maiden all forlorn", "milked"],
            ["man all tattered and torn", "kissed"], ["priest all shaven and shorn", "married"],
            ["rooster that crowed in the morn", "woke"], ["farmer sowing his corn", "kept"],
            ["horse and the hound and the horn", "belonged to"]]

end
