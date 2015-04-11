class FoodChainSong
  ANIMALS = [
    ['fly', "I don't know why she swallowed the fly. Perhaps she'll die."],
    ['spider', 'It wriggled and jiggled and tickled inside her.', 'fly'],
    ['bird', 'How absurd to swallow a bird!', 'spider that wriggled and jiggled and tickled inside her'],
    ['cat', 'Imagine that, to swallow a cat!', 'bird'],
    ['dog', 'What a hog, to swallow a dog!', 'cat'],
    ['goat', 'Just opened her throat and swallowed a goat!', 'dog'],
    ['cow', 'I don\'t know how she swallowed a cow!', 'goat'],
    ['horse', 'She\'s dead, of course!']
  ]

  def song
    ANIMALS.each_with_index.map do |animal, index|
      lines = ["I know an old lady who swallowed a #{animal[0]}."]

      lines << animal[1]

      unless index == 0 || index == 7
        index.downto(1).each do |i|
          previous_animal = ANIMALS[i][2]
          current_animal  = ANIMALS[i][0]
          lines << "She swallowed the #{current_animal} to catch the #{previous_animal}."
        end
      end

      lines << ANIMALS[0][1] unless index == 0 || index == 7

      lines.join("\n") + "\n"
    end
  end

  def verse(n)
    song[n - 1]
  end

  def verses(first, last)
    (first..last).map { |n| verse(n) }.join("\n") + "\n"
  end

  def sing
    verses(1, 8)
  end
end
