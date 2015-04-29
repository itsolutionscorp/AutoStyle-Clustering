class FoodChainSong
  attr_reader :verses_count

  def sing
    verses(1, 8)
  end

  def verse(verses_count)
    @verses_count = verses_count
    compose_song
  end

  def verses(first_verse, last_verse)
    (first_verse..last_verse).reduce('') do |songs, number|
      songs << verse(number) << "\n"
    end
  end

  private

  def compose_song
    cumulative_song = prelude
    cumulative_song << (tragedy_song? ? sad_final : song_continue)
  end

  def song_continue
    continue_song = exlamation
    continue_song << previous_verses
  end

  def previous_verses
    @verses_count.downto(1).reduce('') do |verses, number|
      verses << previous_verse(number)
    end
  end

  def previous_verse(number)
    case number
    when 1 then "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    when 2 then "She swallowed the spider to catch the fly.\n"
    when 3 then "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
    when 4 then "She swallowed the cat to catch the bird.\n"
    when 5 then "She swallowed the dog to catch the cat.\n"
    when 6 then "She swallowed the goat to catch the dog.\n"
    when 7 then "She swallowed the cow to catch the goat.\n"
    end
  end

  def exlamation
    case @verses_count
    when 2 then "It wriggled and jiggled and tickled inside her.\n"
    when 3 then "How absurd to swallow a bird!\n"
    when 4 then "Imagine that, to swallow a cat!\n"
    when 5 then "What a hog, to swallow a dog!\n"
    when 6 then "Just opened her throat and swallowed a goat!\n"
    when 7 then "I don't know how she swallowed a cow!\n"
    else ''
    end
  end

  def animal
    case @verses_count
    when 1 then 'fly'
    when 2 then 'spider'
    when 3 then 'bird'
    when 4 then 'cat'
    when 5 then 'dog'
    when 6 then 'goat'
    when 7 then 'cow'
    when 8 then 'horse'
    end
  end

  def prelude
    "I know an old lady who swallowed a #{ animal }.\n"
  end

  def tragedy_song?
    @verses_count == 8
  end

  def sad_final
    "She's dead, of course!\n"
  end

# mental?)
end
