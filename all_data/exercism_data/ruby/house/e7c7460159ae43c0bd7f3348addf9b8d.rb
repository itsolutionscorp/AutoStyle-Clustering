class House
  def sing
    verses 1
  end

  def verses(start, last = PHRASES.length)
    (start..last).reduce("") do |text, n|
      text << verse(n) << "\n"
    end
  end

  def verse(number)
    (number-1).downto(0).reduce("This is") do |text, index|
      text << PHRASES[index]
    end << "\n"
  end

  private
  def self.phrase(thing, description, prepositional = "")
    " the #{thing} #{prepositional}that #{description}"
  end

  PARTS = [
    ["house", "Jack built."],
    ["malt", "lay in"],
    ["rat", "ate"],
    ["cat", "killed"],
    ["dog", "worried"],
    ["cow", "tossed", "with the crumpled horn "],
    ["maiden", "milked", "all forlorn "],
    ["man", "kissed", "all tattered and torn "],
    ["priest", "married", "all shaven and shorn "],
    ["rooster", "woke", "that crowed in the morn "],
    ["farmer", "kept", "sowing his corn "],
    ["horse", "belonged to", "and the hound and the horn "]
  ]

  PHRASES = PARTS.map { |thing, desc, prep| phrase(thing, desc, prep) }
end










