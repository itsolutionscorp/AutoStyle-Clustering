class FoodChainSong

  ANIMALZ = {
    fly: '',
    spider: "It wriggled and jiggled and tickled inside her.\n",
    bird: "How absurd to swallow a bird!\n",
    cat: "Imagine that, to swallow a cat!\n",
    dog: "What a hog, to swallow a dog!\n",
    goat: "Just opened her throat and swallowed a goat!\n",
    cow: "I don't know how she swallowed a cow!\n",
    horse: "She's dead, of course!\n",
  }
  CLOSER = "I don't know why she swallowed the fly. Perhaps she'll die.\n"

  def initialize
    @keys = ANIMALZ.keys
    @values = ANIMALZ.values
  end

  def verse current_verse
    @current_verse = current_verse - 1

    full_verse = open_sentence
    full_verse << @values[@current_verse]
    return full_verse if @current_verse == 7
    full_verse << middle_stuff
    full_verse << CLOSER
  end

  def open_sentence
    "I know an old lady who swallowed a #{@keys[@current_verse]}.\n"
  end

  def middle_stuff
    middle_song = ''
    @current_verse.downto(1) do |i|
      #special case for bird and spider
      if i == 2
        middle_song += "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
      else
        cur_animal = @keys[i]
        prev_animal = @keys[i - 1]
        middle_song += "She swallowed the #{cur_animal} to catch the #{prev_animal}.\n"
      end
    end
    middle_song
  end

  def verses(start_verse, stop_verse)
    verse_range = start_verse..stop_verse
    sub_song = ''
    verse_range.each do |current_verse|
      sub_song << self.verse(current_verse) + "\n"
    end
    sub_song
  end

  def sing
    self.verses(1,8)
  end

end
