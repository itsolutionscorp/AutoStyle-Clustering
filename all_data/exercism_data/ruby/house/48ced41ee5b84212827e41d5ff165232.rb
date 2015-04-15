class House
  # The nursery rhyme is one sentence that continuously grows by embedding more and more relative clauses as postmodifiers in the noun phrase that ends the sentence:

  DETERMINER = "This is"
  INITIAL_NOUN_PHRASE = "the house that Jack built."
  POSTMODIFIERS =["",
                  "the malt\nthat lay in ",
                  "the rat\nthat ate ",
                  "the cat\nthat killed ",
                  "the dog\nthat worried ",
                  "the cow with the crumpled horn\nthat tossed ",
                  "the maiden all forlorn\nthat milked ",
                  "the man all tattered and torn\nthat kissed ",
                  "the priest all shaven and shorn\nthat married ",
                  "the rooster that crowed in the morn\nthat woke ",
                  "the farmer sowing his corn\nthat kept ",
                  "the horse and the hound and the horn\nthat belonged to "
                ]
  def self.recite
    poem = ""
    noun_phrase = INITIAL_NOUN_PHRASE
    POSTMODIFIERS.each do |post_modifier|
      noun_phrase = post_modifier + noun_phrase
      poem << DETERMINER
      poem << " "
      poem << noun_phrase
      poem << "\n\n"
    end
    poem
  end

end
