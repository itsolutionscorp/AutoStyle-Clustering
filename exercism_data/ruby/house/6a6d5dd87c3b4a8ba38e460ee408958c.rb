class House
  def verse upto_line
    compose_line_of_verse upto_line
  end

  def verses first, last
    verses = ""
    (first..last).each { |verse_number| verses += verse(verse_number) + NEW_LINE }
    verses
  end

  private

  def compose_line_of_verse upto_clause_number
    STEM + build_clauses(upto_clause_number-1)
  end

  STEM = "This is"
  NEW_LINE = "\n"

  CLAUSES = [
    " the house that Jack built.\n",
    ' the malt that lay in',
    " the rat that ate",
    " the cat that killed",
    " the dog that worried",
    " the cow with the crumpled horn that tossed",
    " the maiden all forlorn that milked",
    " the man all tattered and torn that kissed",
    " the priest all shaven and shorn that married",
    " the rooster that crowed in the morn that woke",
    " the farmer sowing his corn that kept",
    " the horse and the hound and the horn that belonged to",
  ]

  def build_clauses last
    stuff = ""
    CLAUSES[0..last].each { |clause| stuff.prepend clause }
    stuff
  end
end
