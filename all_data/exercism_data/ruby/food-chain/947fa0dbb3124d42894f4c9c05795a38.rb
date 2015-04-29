class FoodChainSong
  # verses = [['fly', "I don't know why she swallowed the fly. Perhaps she'll die.\n"],
  #           ['spider', 'It wriggled and jiggled and tickled inside her.\n'],
  #           ['bird', 'How absurd to swallow a bird!\n'],
  #           ['cat', 'Imagine that, to swallow a cat!\n'],
  #           ['dog', 'What a hog, to swallow a dog!\n'],
  #           ['goat', 'Just opened her throat and swallowed a goat!\n'],
  #           ['cow', "I don't know how she swallowed a cow!\n"]]

  def beginning_verse(animal, verse)
    song = ''
    song << "I know an old lady who swallowed a #{animal}.\n"
    song << "#{verse}"
  end

  def middle_verse(animal, previous_animal)
    "She swallowed the #{animal} to catch the #{previous_animal}"
    # repeat
    "It/that wriggled and jiggled and tickled inside her." #if verses[index].first == 'spider'
    'She swallowed the spider to catch the fly.' #if 'spider"
  end

  def verse(stanza)
    verses = [['fly', "I don't know why she swallowed the fly. Perhaps she'll die.\n"],
              ['spider', 'It wriggled and jiggled and tickled inside her.\n'],
              ['bird', 'How absurd to swallow a bird!\n'],
              ['cat', 'Imagine that, to swallow a cat!\n'],
              ['dog', 'What a hog, to swallow a dog!\n'],
              ['goat', 'Just opened her throat and swallowed a goat!\n'],
              ['cow', "I don't know how she swallowed a cow!\n"]]
    1.upto(stanza) do |index|
      song = ''
      song << beginning_verse(verses[index].first, verses[index].last)
      song << middle_verse(verses[index].first, verses[index - 1].first)
      p song
    end
  end

  def self.verses(*stanza)

  end
end
