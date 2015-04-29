class FoodChainSong
  def verse(verse_number)
    case verse_number
    when 8 then horse
    else complex_verse(animals[verse_number - 1])
    end
  end

  def verses(first, last)
    join_lines first.upto(last).each_with_object([]) { |number, verses| verses << verse(number) }
  end

  def sing
    verses 1, 8
  end

private

  ANIMAL_PHRASES = { fly:    nil,
                     spider: "It wriggled and jiggled and tickled inside her.",
                     bird:   "How absurd to swallow a bird!",
                     cat:    "Imagine that, to swallow a cat!",
                     dog:    "What a hog, to swallow a dog!",
                     goat:   "Just opened her throat and swallowed a goat!",
                     cow:    "I don't know how she swallowed a cow!" }

  def animals
    ANIMAL_PHRASES.keys
  end

  def horse
    join_lines i_know_lady_who_swallowed("horse"),
      "She's dead, of course!"
  end

  def complex_verse(animal)
    join_lines i_know_lady_who_swallowed(animal),
      wow(animal),
      catch_sentences(animal),
      "I don't know why she swallowed the fly. Perhaps she'll die."
  end

  def i_know_lady_who_swallowed(animal)
    "I know an old lady who swallowed a #{animal}."
  end

  def wow(animal)
    ANIMAL_PHRASES[animal]
  end

  def catch_sentences(animal)
    targets = animals.take_while { |candidate| candidate != animal }.reverse
    [].tap do |lines|
      targets.zip([animal] + targets).each { |target, catcher| lines << swallowed_to_catch(catcher, target) }
    end
  end

  def swallowed_to_catch(catcher, target)
    "She swallowed the #{catcher} to catch the #{target}#{description target}."
  end

  def description(animal)
    ANIMAL_PHRASES[animal].sub(/It/, " that").chomp(".") if animal == :spider
  end

  def join_lines(*lines)
    "#{lines.flatten.compact.join("\n")}\n"
  end

end
