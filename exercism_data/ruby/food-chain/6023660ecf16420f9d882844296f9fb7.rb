class FoodChainSong
  ASIDES = {
    fly: "I don't know why she swallowed the fly. Perhaps she'll die.",
    spider: 'It wriggled and jiggled and tickled inside her.',
    bird: 'How absurd to swallow a bird!',
    cat: 'Imagine that, to swallow a cat!',
    dog: 'What a hog, to swallow a dog!',
    goat: 'Just opened her throat and swallowed a goat!',
    cow: "I don't know how she swallowed a cow!",
    horse: "She's dead, of course!"
  }

  def verse(n)
    animal, aside = ASIDES.to_a[n - 1]
    ["I know an old lady who swallowed a #{animal}.", aside, *chasers(n)]
      .join("\n") << "\n"
  end

  def verses(from, to)
    from.upto(to).map(&method(:verse)).join("\n") << "\n"
  end

  def sing
    verses(1, ASIDES.length)
  end

  private

  def animal(n)
    ASIDES.keys[n - 1]
  end

  def chasers(n)
    return [] if n == 1 || n == ASIDES.length

    n.downto(2).map do |i|
      "She swallowed the #{animal i} to catch the #{chasing animal i - 1}."
    end << ASIDES.values.first
  end

  def chasing(animal)
    return animal unless ASIDES[animal].start_with?('It')

    ASIDES[animal].sub(/\AIt/, "#{animal} that").chop
  end
end
