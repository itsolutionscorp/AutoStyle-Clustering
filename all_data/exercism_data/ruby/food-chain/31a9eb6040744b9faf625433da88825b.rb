class FoodChainSong
  CREATURES = [
    :fly,
    :spider,
    :bird,
    :cat,
    :dog,
    :goat,
    :cow,
    :horse
  ]

  REMARKS = {
    fly: "I don't know why she swallowed the fly. Perhaps she'll die.",
    spider: "It wriggled and jiggled and tickled inside her.",
    bird: "How absurd to swallow a bird!",
    cat: "Imagine that, to swallow a cat!",
    dog: "What a hog, to swallow a dog!",
    goat: 'Just opened her throat and swallowed a goat!',
    cow: "I don't know how she swallowed a cow!",
    horse: "She's dead, of course!"
  }

  def sing
    verses(1, 8)
  end

  def verses(first, last)
    (first..last).map { |n| verse(n) }.join("\n") + "\n"
  end

  def verse(n)
    creature = CREATURES[n-1]

    output = "I know an old lady who swallowed a #{creature}.\n" \
      "#{REMARKS[creature]}\n"

    # in horse and fly case, just return 'I know an old lady...' + remark
    return output if %i{horse fly}.include?(creature)

    pairs = CREATURES.zip(CREATURES.drop(1)).take(n - 1)
    pairs.reverse.each do |(previous_creature, creature)|
      output << "She swallowed the #{creature} to catch the #{previous_creature}"

      # bird is a special case (includes 'that wriggled and jiggled...')
      if creature == :bird
        output << " that wriggled and jiggled and tickled inside her.\n"
      else
        output << ".\n"
      end
    end

    # last line includes remark about fly
    output + "#{REMARKS[:fly]}\n"
  end
end
