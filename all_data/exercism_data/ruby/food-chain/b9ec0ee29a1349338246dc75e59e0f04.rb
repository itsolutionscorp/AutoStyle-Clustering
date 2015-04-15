class FoodChainSong
  # New line per animal for readability.
  RELATIONS = [['fly', ''],
               ['spider', "It wriggled and jiggled and tickled inside her.\n"],
               ['bird', "How absurd to swallow a bird!\n"],
               ['cat', "Imagine that, to swallow a cat!\n"],
               ['dog', "What a hog, to swallow a dog!\n"],
               ['goat', "Just opened her throat and swallowed a goat!\n"],
               ['cow', "I don't know how she swallowed a cow!\n"],
               ['horse', "She's dead, of course!\n"]]

  def verse(verse_num)
    verse_output = "I know an old lady who swallowed a #{RELATIONS[verse_num - 1][0]}.\n"
    unless verse_num == 1
      verse_output << (verse_num != 1 ? RELATIONS[verse_num - 1][1] : '')
    end
    unless [1, 8].include? verse_num
      RELATIONS[1..(verse_num - 1)].reverse.each do |animal|
        verse_output << "She swallowed the #{animal[0]} to catch the #{RELATIONS[RELATIONS.index(animal) - 1][0]}#{animal[0] == 'bird' ? " that#{RELATIONS[RELATIONS.index(animal) - 1][1][2..-1]}" : ".\n"}"
      end
    end
    verse_output << "I don't know why she swallowed the fly. Perhaps she'll die.\n" unless verse_num == 8

    verse_output
  end

  def verses(verse_start, verse_end)
    verses_output = ''
    (verse_start..verse_end).each { |v| verses_output << (verse(v) + "\n") }

    verses_output
  end

  def sing
    verses(1, 8)
  end
end
