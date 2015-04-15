class FoodChainSong

  ENDING = "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  ANIMALS = [:fly, :spider, :bird, :cat, :dog, :goat, :cow, :horse]
  ANIMAL_DESCRIPTIONS = { spider: "It wriggled and jiggled and tickled inside her.", 
                          bird: "How absurd to swallow a bird!", 
                          cat: "Imagine that, to swallow a cat!", 
                          dog: "What a hog, to swallow a dog!",
                          goat: "Just opened her throat and swallowed a goat!",
                          cow: "I don't know how she swallowed a cow!" }

  def sing
    verses(1,8)
  end

  def verses(first, last)
    first.upto(last).inject('') { |str, verse_number| str + verse(verse_number) + "\n" }
  end

  def verse(verse_number)
    case verse_number
    when 1
      first_verse
    when (2..7)
      full_verse(verse_number - 1)
    when 8
      last_verse
    else
      "Please enter a verse number between 1 and 8"
    end
  end

  private
    def full_verse(verse_number)
      "I know an old lady who swallowed a #{ANIMALS[verse_number]}.\n#{ANIMAL_DESCRIPTIONS[ANIMALS[verse_number]]}\n" +  loop_through_verses(verse_number) + ENDING 
    end

    def loop_through_verses(verse_number)
      song = '' 
      verse_number.downto(1) do |vn|
        song << "She swallowed the #{ANIMALS[vn]} to catch the #{ANIMALS[vn-1]}"
        song << is_eating_spider?(ANIMALS[vn-1])
      end
      song
    end

    def is_eating_spider?(animal)
      animal.eql?(:spider) ? " that wriggled and jiggled and tickled inside her.\n" : ".\n"
    end

    def first_verse
      "I know an old lady who swallowed a fly.\n" + ENDING
    end

    def last_verse
      "I know an old lady who swallowed a horse.\nShe's dead, of course!\n"
    end

end
