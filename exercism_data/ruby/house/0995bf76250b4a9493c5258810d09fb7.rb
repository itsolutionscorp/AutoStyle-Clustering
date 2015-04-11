class House
  def initialize
    @rhyme = ["This is the house that Jack built.",

              "This is the malt that lay in the house that Jack built.",

              "This is the rat that ate the malt that lay in the house that Jack built.",

              "This is the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",

              "This is the horse and the hound and the horn that belonged to the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built."]
  end

  def verse(number)
    "#{@rhyme[number - 1]}\n"
  end

  def verses(first_verse, last_verse)
    verses_to_return = ""
    verses = first_verse - 1
    while verses < last_verse do
      verses_to_return << "#{@rhyme[verses]}\n\n"
      verses += 1
    end
    verses_to_return
  end

end
