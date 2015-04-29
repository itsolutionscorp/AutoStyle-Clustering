class FoodChainSong
  def verse(line)
    raise ArgumentError if line <= 0

    result = if line == 1
               first_sentences
             else
               animals = select_animals(line)
               build_sentences(animals)
             end

    join_sentences(result)
  end

  def verses(first, last)
    join_sentences((first..last).map { |n| verse(n) })
  end

  def sing
    verses(1, 8)
  end

  private

  def select_animals(number)
    swallowed_animals.slice(0, number).reverse
  end

  def join_sentences(sentences)
    sentences.map do |sentence|
      sentence + "\n"
    end.join
  end

  def build_sentences(animals)
    if animals.first == :horse
      first_sentences(:horse)
    else
      build_sentences_for_animals(animals)
    end
  end

  def build_sentences_for_animals(animals)
    first_sentences(animals.first).tap do |sentences|
      animals.each_cons(2).inject(sentences) do |sentences, (first, second)|
        sentences << middle_sentence(first, second)
      end
      sentences << last_sentence
    end
  end

  def swallowed_animals
    swallowed_animals_stories.keys
  end

  def swallowed_animals_stories
    {
      fly: "I don't know why she swallowed the fly. Perhaps she'll die.",
      spider: "It wriggled and jiggled and tickled inside her.",
      bird: "How absurd to swallow a bird!",
      cat: "Imagine that, to swallow a cat!",
      dog: "What a hog, to swallow a dog!",
      goat: "Just opened her throat and swallowed a goat!",
      cow: "I don't know how she swallowed a cow!",
      horse: "She's dead, of course!"
    }
  end

  def first_sentences(animal = :fly)
    [
      "I know an old lady who swallowed a #{animal}.",
      swallowed_animals_stories[animal]
    ]
  end

  def middle_sentence(first, second)
    "She swallowed the #{first} to catch the #{animal_full_name(second)}."
  end

  def animal_full_name(animal)
    if animal == :spider
      "spider that wriggled and jiggled and tickled inside her"
    else
      animal
    end
  end

  def last_sentence
    swallowed_animals_stories[:fly]
  end
end
