class FoodChainSong

  ACTIONS = {"spider" => "wriggled and jiggled and tickled inside her.\n",
             "bird" => "How absurd to swallow a bird!\n",
             "cat" => "Imagine that, to swallow a cat!\n",
             "dog" => "What a hog, to swallow a dog!\n",
             "goat" => "Just opened her throat and swallowed a goat!\n",
             "cow" => "I don't know how she swallowed a cow!\n"


  }

  REASONS = ["I don't know why she swallowed the fly. Perhaps she'll die.\n",
             "She swallowed the spider to catch the fly.\n",
             "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
             "She swallowed the cat to catch the bird.\n",
             "She swallowed the dog to catch the cat.\n",
             "She swallowed the goat to catch the dog.\n",
             "She swallowed the cow to catch the goat.\n"
  ]

  CRITTERS = ["fly",
              "spider",
              "bird",
              "cat",
              "dog",
              "goat",
              "cow",
              "horse"
  ]

  def verse(number)
    if number == 1
      return "#{animal_swallowed(number)}#{reason_countdown(number)}"
    end
    if number == 2
      return "#{animal_swallowed(number)}It #{action(number)}#{reason_countdown(number)}"
    end
    if number == 8
      return "#{animal_swallowed(number)}She's dead, of course!\n"
    end
    # verses 3-7
    return "#{animal_swallowed(number)}#{action(number)}#{reason_countdown(number)}"
  end

  def verses(from, to)
    str = ""
    (from..to).each do |num|
      str += "#{verse(num)}\n"
    end
    str
  end

  def sing
    verses(1,8)
  end

  private

  def animal_swallowed(number)
    "I know an old lady who swallowed a #{CRITTERS[number-1]}.\n"
  end

  def reason_countdown(number)
    str = ""
    number.downto(1) do |num|
      str << REASONS[num-1]
    end
    str
  end

  def action(number)
    "#{ACTIONS[CRITTERS[number-1]]}"
  end

end
