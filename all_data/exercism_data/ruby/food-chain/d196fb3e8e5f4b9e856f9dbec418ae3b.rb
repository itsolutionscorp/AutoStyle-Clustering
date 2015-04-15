class FoodChainSong
  def sing
    verses(1, 8)
  end

  def verse(verses_count)
    compose_cumulative_song(verses_count)
  end

  def verses(first_verse, last_verse)
    (first_verse..last_verse).reduce('') do |long_song, number|
      long_song << verse(number) << "\n"
    end
  end
end

def compose_cumulative_song(animal_number)
  swallow_animal = animal(animal_number)
  cumulative_song = prelude_about_lady(swallow_animal)

  if horse?(swallow_animal)
    cumulative_song << tragedy_final
  else
    cumulative_song << exclamation(swallow_animal)
    cumulative_song << swallows_story(animal_number)
  end
end

def swallows_story(animals_count)
  [
    '',
    "I don't know why she swallowed the fly. Perhaps she'll die.\n",
    "She swallowed the spider to catch the fly.\n",
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
    "She swallowed the cat to catch the bird.\n",
    "She swallowed the dog to catch the cat.\n",
    "She swallowed the goat to catch the dog.\n",
    "She swallowed the cow to catch the goat.\n"
  ].values_at(1..animals_count).reverse.join
end

def exclamation(swallow_animal)
  case swallow_animal
  when 'spider' then "It wriggled and jiggled and tickled inside her.\n"
  when 'bird' then "How absurd to swallow a bird!\n"
  when 'cat' then "Imagine that, to swallow a cat!\n"
  when 'dog' then "What a hog, to swallow a dog!\n"
  when 'goat' then "Just opened her throat and swallowed a goat!\n"
  when 'cow' then "I don't know how she swallowed a cow!\n"
  else ''
  end
end

def animal(number)
  case number
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

def horse?(swallow_animal)
  swallow_animal == 'horse'
end

def prelude_about_lady(swallow_animal)
  "I know an old lady who swallowed a #{ swallow_animal }.\n"
end

def tragedy_final
  "She's dead, of course!\n"
end
