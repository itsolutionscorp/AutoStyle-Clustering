class FoodChainSong

  LINES = [
    { critter: "fly", line: "I don't know why she swallowed the fly. Perhaps she'll die.\n" },
    { critter: "spider", line: "It wriggled and jiggled and tickled inside her." },
    { critter: "bird", line: "How absurd to swallow a bird!" },
    { critter: "cat", line: "Imagine that, to swallow a cat!" },
    { critter: "dog", line: "What a hog, to swallow a dog!"},
    { critter: "goat", line: "Just opened her throat and swallowed a goat!" },
    { critter: "cow", line: "I don't know how she swallowed a cow!" },
    { critter: "horse", line: "She's dead, of course!\n" }
  ]

  def sing
    verses(1,8)
  end

  def verses(start,stop)
    (start..stop).to_a.map{ |verse_index| verse(verse_index) }.join("\n") + "\n"
  end

  def verse(index)
    lines = [ swallowed(LINES[index-1][:critter]), LINES[index-1][:line] ]

    # first and last index only have 2 lines
    unless index == LINES.size || index == 1

      (index-1).downto(1).each do |line|
        lines << swallowed_to_catch(LINES[line][:critter], LINES[line-1][:critter])
      end

      lines << LINES.first[:line]

    end

    lines.join("\n")
  end

  def swallowed(critter)
    "I know an old lady who swallowed a #{critter}."
  end

  def swallowed_to_catch(first_critter, second_critter)
    "She swallowed the #{first_critter} to catch the #{second_critter}" +
      (second_critter == 'spider' ? ' that wriggled and jiggled and tickled inside her' : '') +
        '.'
  end

end
