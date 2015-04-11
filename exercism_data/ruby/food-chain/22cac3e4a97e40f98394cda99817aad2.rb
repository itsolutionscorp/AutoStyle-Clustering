class FoodChainSong
  LAST_LINE = "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  ANIMAL_REMARKS = [
    ["fly"],
    ["spider", "It wriggled and jiggled and tickled inside her."],
    ["bird", "How absurd to swallow a bird!"],
    ["cat", "Imagine that, to swallow a cat!"],
    ["dog", "What a hog, to swallow a dog!"],
    ["goat", "Just opened her throat and swallowed a goat!"],
    ["cow", "I don't know how she swallowed a cow!"],
    ["horse", "She's dead, of course!"],
  ]

  def initialize
    @last_animal = animal_for_verse(ANIMAL_REMARKS.length)
  end

  def verse(n)
    animal, remark = animal_for_verse(n), remark_for_verse(n)
    verses = [first_line(animal)]
    verses << remark
    if final_animal?(animal)
      join_verses(verses)
    else
      chain = food_chain(n)
      verses = verses.concat(chain)
      joined_verses = join_verses(verses)
      joined_verses + LAST_LINE
    end
  end

  def verses(start, stop)
    (start..stop).map { |verse_number| verse(verse_number) }.join("\n") + "\n"
  end

  def sing
    verses(1, ANIMAL_REMARKS.length)
  end

  private

  def animal_for_verse(verse)
    ANIMAL_REMARKS[verse - 1][0]
  end

  def remark_for_verse(verse)
    ANIMAL_REMARKS[verse - 1][1]
  end

  def final_animal?(animal)
    animal == @last_animal
  end

  def first_line(animal)
   "I know an old lady who swallowed a #{animal}."
  end

  def food_chain(n)
    chain = []
    previous_animal = animal_for_verse(n)
    while n > 1
      n -= 1
      current_animal = previous_animal
      previous_animal = animal_for_verse(n)
      extra = nil
      extra = " that wriggled and jiggled and tickled inside her" if previous_animal == "spider"
      chain << "She swallowed the #{current_animal} to catch the #{previous_animal}#{extra}."
    end
    chain
  end

  def join_verses(verses)
   verses.compact.join("\n") + "\n"
  end
end
