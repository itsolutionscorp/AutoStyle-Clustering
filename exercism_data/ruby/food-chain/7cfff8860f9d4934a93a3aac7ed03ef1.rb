class FoodChainSong
  ANIMALS = %w(fly spider bird cat dog goat cow horse)
  WRIGGLED = 'wriggled and jiggled and tickled inside her'
  COMMENTS = [
    '',
    "It #{WRIGGLED}.",
    'How absurd to swallow a bird!',
    'Imagine that, to swallow a cat!',
    'What a hog, to swallow a dog!',
    'Just opened her throat and swallowed a goat!',
    "I don't know how she swallowed a cow!",
    "She's dead, of course!"
  ]

  def verse(n)
    text = "I know an old lady who swallowed a #{ANIMALS[n - 1]}.\n"
    if n > 1
      text << "#{COMMENTS[n - 1]}\n"
      if n < ANIMALS.length
        (2..n).reverse_each do |i|
          current = ANIMALS[i - 1]
          previous = ANIMALS[i - 2]
          wriggled = " that #{WRIGGLED}" if previous == 'spider'
          text << "She swallowed the #{current} to catch the #{previous}#{wriggled}.\n"
        end
      end
    end
    if n < ANIMALS.length
      text << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end
    text
  end

  def verses(start, finish)
    (start..finish).map do |n|
      verse(n)
    end.join("\n") + "\n"
  end

  def sing
    verses(1, 8)
  end
end
