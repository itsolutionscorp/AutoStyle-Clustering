class House
  def verse upto_clause
    compose_line_of_verse upto_clause
  end

  def verses first_verse, last_verse
    (first_verse..last_verse)
    .map { |upto_clause| compose_line_of_verse(upto_clause) + NEW_LINE }.join
  end

  private

  def compose_line_of_verse upto_clause
    LINE_STEM + build_line_clauses(upto_clause)
  end

  LINE_STEM = "This is"
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

  def build_line_clauses last_clause
    CLAUSES.first(last_clause).reverse.join
  end
end
