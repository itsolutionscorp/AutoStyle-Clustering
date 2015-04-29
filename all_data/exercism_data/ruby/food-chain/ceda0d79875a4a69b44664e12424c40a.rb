class FoodChainSong



  def initialize()
    @full_verse = Array.new()
    @full_verse[1] = "I know an old lady who swallowed a fly.\n"
    @full_verse[2] = "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n"
    @full_verse[3] = "I know an old lady who swallowed a bird.\nHow absurd to swallow a bird!\n"
    @full_verse[4] = "I know an old lady who swallowed a cat.\nImagine that, to swallow a cat!\n"
    @full_verse[5] = "I know an old lady who swallowed a dog.\nWhat a hog, to swallow a dog!\n"
    @full_verse[6] = "I know an old lady who swallowed a goat.\nJust opened her throat and swallowed a goat!\n"
    @full_verse[7] = "I know an old lady who swallowed a cow.\nI don't know how she swallowed a cow!\n"
    @full_verse[8] = "I know an old lady who swallowed a horse.\nShe's dead, of course!\n"

    @short_verse = Array.new()
    @short_verse[2] = "She swallowed the spider to catch the fly."
    @short_verse[3] = "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her."
    @short_verse[4] = "She swallowed the cat to catch the bird."
    @short_verse[5] = "She swallowed the dog to catch the cat."
    @short_verse[6] = "She swallowed the goat to catch the dog."
    @short_verse[7] = "She swallowed the cow to catch the goat."

    @last_verse = "I don't know why she swallowed the fly. Perhaps she'll die.\n"

  end

  def verse(verse_number)
    song = @full_verse[verse_number]

    if verse_number != 8
      (2..verse_number).reverse_each { |n| song += @short_verse[n] + "\n" }
      song += @last_verse
    end

    return song
  end

  def verses(first, last)
    song = (first..last).collect { |n| verse(n) }.join("\n")
    return song + "\n"
  end

  def sing()
    verses(1, 8)
  end

end
