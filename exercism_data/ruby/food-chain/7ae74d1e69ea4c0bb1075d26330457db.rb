class FoodChainSong
  ANIMALS = %w(fly spider bird cat dog goat cow horse)
  VERSES = {
    spider: "It wriggled and jiggled and tickled inside her.",
    bird: "How absurd to swallow a bird!",
    cat: "Imagine that, to swallow a cat!",
    dog: "What a hog, to swallow a dog!",
    goat: "Just opened her throat and swallowed a goat!",
    cow: "I don't know how she swallowed a cow!",
    horse: "She's dead, of course!"
  }

  def verse(num)
    @which = num - 1
    @count = @which
    song = []
    song << opening
    song << special unless special.nil?
    return song.join("\n") << "\n" if animal == "horse"
    while @count > 0
      song << middle
      @count -= 1
    end
    song << denoument
    return song.join("\n") << "\n"
  end

  def verses(start,stop)
    song = ""
    (start..stop).each do |i|
      song << verse(i) << "\n"
    end
    song
  end

  def sing
    verses(1,8)
  end

  private

  def opening
    "I know an old lady who swallowed a #{animal}."
  end

  def special
    VERSES[animal.to_sym]
  end

  def middle
    part = "She swallowed the #{animal} to catch the #{prev_animal}"
    if prev_animal == "spider"
      part << " that wriggled and jiggled and tickled inside her."
    else
      part << "."
    end
    part
  end

  def denoument
    "I don't know why she swallowed the fly. Perhaps she'll die."
  end

  def animal
    ANIMALS[@count]
  end

  def prev_animal
    ANIMALS[@count-1]
  end
end
