class FoodChainSong

  def sing
    verses(1, 8)
  end

  def verses(start_verse, end_verse)
    (start_verse..end_verse).map { |x| verse(x) }.join("\n") << "\n"
  end

  def verse(verse_number)
    return end_of_song if verse_number == 8
    return start_of_song if verse_number == 1
    verse_number -= 1 # Adjust for array index
    song = no_reason(verse_number)
    verse_number.downto(1) { |x| song << all_verses[x].reason }
    song << all_verses[0].comment
  end

  private

  def all_verses
    @verses ||= [
      Verse.new('fly', "I don't know why she swallowed the fly. Perhaps she'll die.", ""),
      Verse.new('spider', "It wriggled and jiggled and tickled inside her.", "She swallowed the spider to catch the fly."),
      Verse.new('bird', "How absurd to swallow a bird!", "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her."),
      Verse.new('cat', "Imagine that, to swallow a cat!", 'She swallowed the cat to catch the bird.'),
      Verse.new('dog', "What a hog, to swallow a dog!", 'She swallowed the dog to catch the cat.'),
      Verse.new('goat', "Just opened her throat and swallowed a goat!", 'She swallowed the goat to catch the dog.'),
      Verse.new('cow', "I don't know how she swallowed a cow!", 'She swallowed the cow to catch the goat.'),
      Verse.new('horse', "She's dead, of course!", "")
    ]
  end

  def end_of_song
    no_reason(7)
  end

  def start_of_song
    no_reason(0)
  end

  def no_reason(verse_number)
    all_verses[verse_number].start + all_verses[verse_number].comment
  end

  class Verse
    def initialize(animal, comment, reason)
      @animal = animal
      @comment = comment
      @reason = reason
    end

    def start
      "I know an old lady who swallowed a #@animal.\n"
    end

    def comment
      "#@comment\n"
    end

    def reason
      "#@reason\n"
    end
  end
end
