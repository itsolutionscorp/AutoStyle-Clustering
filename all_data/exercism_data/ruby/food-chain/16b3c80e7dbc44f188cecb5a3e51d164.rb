class FoodChainSong

  VERSE_DATA = {
    1 => { animal: "fly", reaction: "", affect: ""},
    2 => { animal: "spider", reaction: "It wriggled and jiggled and tickled inside her.\n", affect: " that wriggled and jiggled and tickled inside her"},
    3 => { animal: "bird", reaction: "How absurd to swallow a bird!\n", affect: ""},
    4 => { animal: "cat", reaction: "Imagine that, to swallow a cat!\n", affect: ""},
    5 => { animal: "dog", reaction: "What a hog, to swallow a dog!\n", affect: ""},
    6 => { animal: "goat", reaction: "Just opened her throat and swallowed a goat!\n", affect: ""},
    7 => { animal: "cow", reaction: "I don't know how she swallowed a cow!\n", affect: ""},
    8 => { animal: "horse", reaction: "She's dead, of course!\n", affect: ""}
  }

  def verse number
    song = "I know an old lady who swallowed a #{VERSE_DATA[number][:animal]}.\n"
    song << VERSE_DATA[number][:reaction]

    unless number == 8
      unless number == 1
        number.downto(2) do |i|
          song << "She swallowed the #{VERSE_DATA[i][:animal]} to catch the #{VERSE_DATA[i-1][:animal]}#{VERSE_DATA[i-1][:affect]}.\n"
        end
      end
      song <<  "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end

    song
  end

  def verses start_verse, end_verse
    song = ""
    start_verse.upto(end_verse) do |n|
      song << verse(n) + "\n"
    end
    song
  end

  def sing
    verses(1, 8)
  end
end
