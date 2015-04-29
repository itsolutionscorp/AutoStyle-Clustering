require 'ostruct'

class BeerSong
  def verse(verses)
    if last_verse?(verses)
      last_verse_template
    else
      verse_template(model(verses))
    end
  end

  def verses(starting_verse, verses_to_sing=0)
    song = ""
    starting_verse.downto(verses_to_sing).each do |n|
      song += verse(n) + "\n"
    end
    song
  end

  private
  def verse_template(verse_model)
    "#{verse_model.verses} bottle#{verse_model.plural} of beer on the wall, " +
    "#{verse_model.verses} bottle#{verse_model.plural} of beer.\n" +
    "Take #{verse_model.one} down and pass it around, " +
    "#{verse_model.next} bottle#{verse_model.next_plural} of beer on the wall.\n"
  end

  def last_verse_template
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the " +
    "store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def model(verses)
     if one_verse?(verses)
       last_verse
     else
       standard_verse(verses)
     end
  end

  def standard_verse(verses)
    next_verse = verses - 1
    next_plural = one_verse?(next_verse) ? "" : "s"
    OpenStruct.new(
      verses: verses,
      plural: "s",
      one: "one",
      next: next_verse,
      next_plural: next_plural
    )
  end

  def last_verse
    OpenStruct.new(
      verses: 1,
      plural: "",
      one: "it",
      next: "no more",
      next_plural: "s"
    )
  end

  def one_verse?(verses)
    verses == 1
  end

  def last_verse?(verses)
    verses == 0
  end
end
