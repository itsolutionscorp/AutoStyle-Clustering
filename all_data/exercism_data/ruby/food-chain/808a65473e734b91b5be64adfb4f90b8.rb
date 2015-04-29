class FoodChainSong
  def verse(n)
    verse = first_line(meal(n))
    return verse + last_line if n == 1
    return verse + "She's dead, of course!\n" if n == 8

    verse += effect(n-2)
    verse += build_verses(n)
    verse += last_line
    verse
  end

  def verses(start, stop)
    result = ""
    (start..stop).to_a.each do |i|
      result += verse(i) + "\n"
    end
    result
  end

  def sing
    verses(1,8)
  end

  private

  def meal(n)
    prey[n-1]
  end

  def build_verses(n)
    chain = []
    i = 0
    while i < n - 1
      chain << motivation(i)
      i += 1
    end
    chain.reverse.join
  end

  def first_line(meal)
    "I know an old lady who swallowed a #{meal}.\n"
  end

  def effect(n)
    "#{effects[n]}\n"
  end

  def motivation(i)
    motivation = "She swallowed the #{prey[i+1]} to catch the #{prey[i]}"
    motivation +=  " that wriggled and jiggled and tickled inside her" if i == 1
    motivation + ".\n"
  end


  def prey
    ["fly",
     "spider",
     "bird",
     "cat",
     "dog",
     "goat",
     "cow",
     "horse"]
  end

  def effects
    ["It wriggled and jiggled and tickled inside her.",
     "How absurd to swallow a bird!",
     "Imagine that, to swallow a cat!",
     "What a hog, to swallow a dog!",
     "Just opened her throat and swallowed a goat!",
     "I don't know how she swallowed a cow!"]
  end

  def last_line
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end
end
