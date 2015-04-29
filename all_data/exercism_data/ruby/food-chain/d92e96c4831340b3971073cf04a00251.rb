class FoodChainSong
  ANIMALS = [
    {name: "fly", reaction: "I don't know why she swallowed the fly. Perhaps she'll die."},
    {name: "spider", reaction: "It wriggled and jiggled and tickled inside her."},
    {name: "bird", reaction: "How absurd to swallow a bird!"},
    {name: "cat", reaction: "Imagine that, to swallow a cat!"},
    {name: "dog", reaction: "What a hog, to swallow a dog!"},
    {name: "goat", reaction: "Just opened her throat and swallowed a goat!"},
    {name: "cow", reaction: "I don't know how she swallowed a cow!"},
    {name: "horse", reaction: "She's dead, of course!"}
  ]

  def verse(num)
    num -= 1 # For zero-indexed ANIMALS
    lines = intro_for(num)
    # Don't include the chorus or outro for "fly" or "horse"
    unless num == 0 || num == 7
      num.downto(1).each{|index| lines << chorus_for(index)}
      lines << outro
    end
    lines.join("\n")+"\n"
  end

  def verses(from, to)
    (from..to).map{|num| verse(num)}.join
  end

  def sing
    verses(1, 8)
  end

private

  def intro_for(index)
    animal = ANIMALS[index]
    ["I know an old lady who swallowed a #{animal[:name]}.", animal[:reaction]]
  end

  def chorus_for(index)
    animal = ANIMALS[index][:name]
    food = ANIMALS[index-1][:name]
    if food == "spider"
      food = "spider that wriggled and jiggled and tickled inside her"
    end
    "She swallowed the #{animal} to catch the #{food}."
  end

  def outro
    ANIMALS[0][:reaction]
  end

end
