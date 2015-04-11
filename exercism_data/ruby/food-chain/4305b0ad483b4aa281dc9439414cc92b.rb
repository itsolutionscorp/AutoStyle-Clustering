class FoodChainSong

  ANIMALS = {
    1 => 'fly', 
    2 => 'spider',
    3 => 'bird',
    4 => 'cat',
    5 => 'dog',
    6 => 'goat',
    7 => 'cow',
    8 => 'horse'
  }

  FIRST_RHYME = {
    2 => 'It wriggled and jiggled and tickled inside her.',
    3 => 'How absurd to swallow a bird!',
    4 => 'Imagine that, to swallow a cat!',
    5 => 'What a hog, to swallow a dog!',
    6 => 'Just opened her throat and swallowed a goat!',
    7 => "I don't know how she swallowed a cow!",
    8 => "She's dead, of course!"
  }

  SECOND_RHYME = {
    1 => "I don't know why she swallowed the fly. Perhaps she'll die.",
    2 => "She swallowed the spider to catch the fly.",
    3 => "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    4 => "She swallowed the cat to catch the bird.",
    5 => "She swallowed the dog to catch the cat.",
    6 => "She swallowed the goat to catch the dog.",
    7 => "She swallowed the cow to catch the goat."
  }

  def verse(verse_number)
    output = ''
    output << "I know an old lady who swallowed a #{ANIMALS[verse_number]}.\n"
    output << FIRST_RHYME[verse_number] + "\n" if FIRST_RHYME[verse_number]
    (1..verse_number).reverse_each { |i| output << (SECOND_RHYME[i] + "\n")} if SECOND_RHYME[verse_number]

    output
  end

  def verses(first, last)
    output = ''
    (first..last).each {|number| output << (verse(number) + "\n") }

    output
  end

  def sing
    verses(1, 8)
  end
end

puts FoodChainSong.new.verse(1)
