class FoodChainSong
  CREATURES = [
    'fly',
    'spider',
    'bird',
    'cat',
    'dog',
    'goat',
    'cow',
    'horse',
  ]

  EXTRAS = {
    'spider' => 'It wriggled and jiggled and tickled inside her.',
    'bird' => 'How absurd to swallow a bird!',
    'cat' => 'Imagine that, to swallow a cat!',
    'dog' => 'What a hog, to swallow a dog!',
    'goat' => 'Just opened her throat and swallowed a goat!',
    'cow' => 'I don\'t know how she swallowed a cow!',
    'horse' => 'She\'s dead, of course!'
  }

  def verse(n)
    creature = CREATURES[n-1]

    lines = []
    lines.push("I know an old lady who swallowed a #{creature}.")

    extra = EXTRAS[creature]
    if extra
      lines.push(extra)
    end

    if creature != 'horse'
      (n-1).downto(1).each do |i|
        predator = CREATURES[i]
        prey = CREATURES[i-1]

        wriggled = (prey == 'spider' ?
                    ' that wriggled and jiggled and tickled inside her' :
                    '')
        lines.push("She swallowed the #{predator} to catch the #{prey}#{wriggled}.")
      end

      lines.push("I don't know why she swallowed the fly. Perhaps she'll die.")
    end


    lines.join("\n") + "\n"
  end

  def verses(first, last)
    # All verses between first and last, inclusive
    # (using a 1-based index)
    first.upto(last).collect { |i| verse(i) }.join("\n") + "\n"
  end

  def sing
    # Sing all the verses
    verses(1, CREATURES.length)
  end
end
