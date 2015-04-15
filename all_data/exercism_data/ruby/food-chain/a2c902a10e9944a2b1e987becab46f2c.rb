class FoodChainSong
  ANIMALS = {
    fly: "",
    spider: "It wriggled and jiggled and tickled inside her.\n",
    bird: "How absurd to swallow a bird!\n",
    cat: "Imagine that, to swallow a cat!\n",
    dog: "What a hog, to swallow a dog!\n",
    goat: "Just opened her throat and swallowed a goat!\n",
    cow: "I don't know how she swallowed a cow!\n",
    horse: "She's dead, of course!\n"
  }

  def sing
    verses(1, 8)
  end

  def verses(from, to)
    output = (from..to).reduce("") do |output, num|
      output + verse(num) + "\n"
    end
    output
  end

  def verse(num)
    return top(8) if num == 8
    top(num) + middle(num) + bottom
  end

  private

  def top(num)
    animal = ANIMALS.keys[num - 1]
    "I know an old lady who swallowed a #{animal.to_s}.\n#{ANIMALS[animal]}"
  end

  def middle(num)
    return "" if num == 1
    ANIMALS.keys.slice(0, num).reverse.each_cons(2).reduce("") do |output, (first, second)|
      spider_modifier = second == :spider ? " that wriggled and jiggled and tickled inside her" : ""
      output + "She swallowed the #{first.to_s} to catch the #{second.to_s}#{spider_modifier}.\n"
    end
  end

  def bottom
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end
end
