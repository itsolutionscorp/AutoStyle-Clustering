class FoodChainSong
  def verse(num)
    "#{first_phrase(num)}" \
    "#{second_phrase(num)}" \
    "#{food_chains(num)}" \
    "#{conjunction(num)}" \
    "#{wriggle_jiggle(num)}" \
    "#{last_food_chain(num)}" \
    "#{last_phrase(num)}"
  end

  def verses(v1, v2)
    "#{verse(v1)}" \
    "\n" \
    "#{verse(v2)}" \
    "\n"
  end

  def sing
    verses(1, 8)
  end

  def food(num)
    case num
    when 1
      "fly"
    when 2
      "spider"
    when 3
      "bird"
    when 4
      "cat"
    when 5
      "dog"
    when 6
      "goat"
    when 7
      "cow"
    when 8
      "horse"
    end
  end

  def first_phrase(num)
    "I know an old lady who swallowed a #{food(num)}.\n"
  end

  def second_phrase(num)
    case num
    when 3
      "How absurd to swallow a #{food(num)}!\n"
    when 4
      "Imagine that, to swallow a #{food(num)}!\n"
    when 5
      "What a hog, to swallow a #{food(num)}!\n"
    when 6
      "Just opened her throat and swallowed a #{food(num)}!\n"
    when 7
      "I don't know how she swallowed a #{food(num)}!\n"
    else
      nil
    end
  end

  def food_chains(num)
    case num
    when 3
      "She swallowed the #{food(num)} to catch the #{food(num-1)} "
    when 4
      "She swallowed the #{food(num)} to catch the #{food(num-1)}.\n" \
      "She swallowed the #{food(num-1)} to catch the #{food(num-2)} " \
    when 5
      "She swallowed the #{food(num)} to catch the #{food(num-1)}.\n" \
      "She swallowed the #{food(num-1)} to catch the #{food(num-2)}.\n" \
      "She swallowed the #{food(num-2)} to catch the #{food(num-3)} " \
    when 6
      "She swallowed the #{food(num)} to catch the #{food(num-1)}.\n" \
      "She swallowed the #{food(num-1)} to catch the #{food(num-2)}.\n" \
      "She swallowed the #{food(num-2)} to catch the #{food(num-3)}.\n" \
      "She swallowed the #{food(num-3)} to catch the #{food(num-4)} " \
    when 7
      "She swallowed the #{food(num)} to catch the #{food(num-1)}.\n" \
      "She swallowed the #{food(num-1)} to catch the #{food(num-2)}.\n" \
      "She swallowed the #{food(num-2)} to catch the #{food(num-3)}.\n" \
      "She swallowed the #{food(num-3)} to catch the #{food(num-4)}.\n" \
      "She swallowed the #{food(num-4)} to catch the #{food(num-5)} " \
    else
      nil
    end
  end

  def conjunction(num)
    case num
    when 1
      nil
    when 2
      "It "
    when 8
      nil
    else
      "that "
    end
  end

  def wriggle_jiggle(num)
    case num
    when 1
      nil
    when 8
      nil
    else
      "wriggled and jiggled and tickled inside her.\n"
    end
  end

  def last_food_chain(num)
    case num
    when 1
      nil
    when 8
      nil
    else
      "She swallowed the #{food(num-(num-2))} to catch the #{food(num-(num-1))}.\n"
    end
  end

  def last_phrase(num)
    case num
    when 8
      "She's dead, of course!\n"
    else
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end
  end
end
