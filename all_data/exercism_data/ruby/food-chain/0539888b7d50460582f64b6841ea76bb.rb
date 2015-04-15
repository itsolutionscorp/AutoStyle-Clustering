require 'pry'
class FoodChainSong
  SONG_VERSE_LINES = {
    1 => { first: "I know an old lady who swallowed a fly.\n",
           last: "I don't know why she swallowed the fly. Perhaps she'll die.\n" },
    2 => { first: "I know an old lady who swallowed a spider.\n",
           second: "It wriggled and jiggled and tickled inside her.\n",
           last: "She swallowed the spider to catch the fly.\n" },
    3 => { first: "I know an old lady who swallowed a bird.\n",
           second: "How absurd to swallow a bird!\n",
           last: "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" },
    4 => { first: "I know an old lady who swallowed a cat.\n",
           second: "Imagine that, to swallow a cat!\n",
           last: "She swallowed the cat to catch the bird.\n" },
    5 => { first: "I know an old lady who swallowed a dog.\n",
           second: "What a hog, to swallow a dog!\n",
           last: "She swallowed the dog to catch the cat.\n" },
    6 => { first: "I know an old lady who swallowed a goat.\n",
           second: "Just opened her throat and swallowed a goat!\n",
           last: "She swallowed the goat to catch the dog.\n" },
    7 => { first: "I know an old lady who swallowed a cow.\n",
           second: "I don't know how she swallowed a cow!\n",
           last: "She swallowed the cow to catch the goat.\n" },
    8 => { first: "I know an old lady who swallowed a horse.\n",
           last: "She's dead, of course!\n" }
  }

  def verse(line_num)
    song = ''
    if line_num == 8
      SONG_VERSE_LINES[line_num].values.each { |x| song << x }
      song
    else
      song << SONG_VERSE_LINES[line_num].reject { |x| x == :last }.values.join
      line_num.downto(1) { |x| song << verse_end(x) }
    end
    song
  end

  def verse_end(line_num)
    SONG_VERSE_LINES[line_num][:last]
  end

  def verses(first_verse, last_verse)
    # refactor idea => how can I push this back through my verse method
    song = ''
    (first_verse..last_verse).each { |x| song << verse(x) + "\n" }
    song
  end

  def sing
    verses(1, 8)
  end
end
