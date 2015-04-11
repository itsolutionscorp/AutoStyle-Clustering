class House
  def sing
    verses 1
  end

  def verses(start, last = PARTS.length)
    (start..last).map{ |n| verse(n) }.join("\n") << "\n"
  end

  def verse(number)
    (["This is"] +
      (number - 1).downto(0).map{ |i| House.phrase(i) }).join(" ") << "\n"
  end

  private
  def self.phrase(i)
    @@phrases[i] ||= begin
      thing, description, prepositional = PARTS[i]
      "the #{thing} #{prepositional} that #{description}".squeeze " "
    end
  end

  PARTS = [
    ["house", "Jack built."],
    ["malt", "lay in"],
    ["rat", "ate"],
    ["cat", "killed"],
    ["dog", "worried"],
    ["cow", "tossed", "with the crumpled horn"],
    ["maiden", "milked", "all forlorn"],
    ["man", "kissed", "all tattered and torn"],
    ["priest", "married", "all shaven and shorn"],
    ["rooster", "woke", "that crowed in the morn"],
    ["farmer", "kept", "sowing his corn"],
    ["horse", "belonged to", "and the hound and the horn"]
  ]

  @@phrases = []
end
