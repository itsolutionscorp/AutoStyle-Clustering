require 'erb'

require 'active_support'
require 'active_support/core_ext'

module FoodChainSongTemplate
  ANIMAL_HIERARCHY = {
    1 => 'fly',
    2 => 'spider',
    3 => 'bird',
    4 => 'cat',
    5 => 'dog',
    6 => 'goat',
    7 => 'cow',
    8 => 'horse'
  }

  # Template Partials
  FIRST_VERSE_PART = 'I know an old lady who swallowed a <%= ANIMAL_HIERARCHY[@first_animal_swallowed] %>.'
  SECOND_VERSE_PART = 'She swallowed the spider to catch the fly.'
  THIRD_VERSE_PART = 'She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.'
  FOURTH_VERSE_PART = 'She swallowed the cat to catch the bird.'
  FIFTH_VERSE_PART = 'She swallowed the dog to catch the cat.'
  SIXTH_VERSE_PART = 'She swallowed the goat to catch the dog.'
  SEVENTH_VERSE_PART = 'She swallowed the cow to catch the goat.'
  LAST_VERSE_PART = "I don't know why she swallowed the fly. Perhaps she'll die."

  # Templates
  FLY_TEMPLATE = "#{FIRST_VERSE_PART}\n#{LAST_VERSE_PART}\n"

  SECOND_VERSE_TEMPLATE = <<EOS
#{FIRST_VERSE_PART}
It wriggled and jiggled and tickled inside her.
#{SECOND_VERSE_PART}
#{LAST_VERSE_PART}
EOS

  THIRD_VERSE_TEMPLATE = <<EOS
#{FIRST_VERSE_PART}
How absurd to swallow a bird!
#{THIRD_VERSE_PART}
#{SECOND_VERSE_PART}
#{LAST_VERSE_PART}
EOS

  FOURTH_VERSE_TEMPLATE = <<EOS
#{FIRST_VERSE_PART}
Imagine that, to swallow a cat!
#{FOURTH_VERSE_PART}
#{THIRD_VERSE_PART}
#{SECOND_VERSE_PART}
#{LAST_VERSE_PART}
EOS

  FIFTH_VERSE_TEMPLATE = <<EOS
#{FIRST_VERSE_PART}
What a hog, to swallow a dog!
#{FIFTH_VERSE_PART}
#{FOURTH_VERSE_PART}
#{THIRD_VERSE_PART}
#{SECOND_VERSE_PART}
#{LAST_VERSE_PART}
EOS

  SIXTH_VERSE_TEMPLATE = <<EOS
#{FIRST_VERSE_PART}
Just opened her throat and swallowed a goat!
#{SIXTH_VERSE_PART}
#{FIFTH_VERSE_PART}
#{FOURTH_VERSE_PART}
#{THIRD_VERSE_PART}
#{SECOND_VERSE_PART}
#{LAST_VERSE_PART}
EOS

  SEVENTH_VERSE_TEMPLATE = <<EOS
#{FIRST_VERSE_PART}
I don't know how she swallowed a cow!
#{SEVENTH_VERSE_PART}
#{SIXTH_VERSE_PART}
#{FIFTH_VERSE_PART}
#{FOURTH_VERSE_PART}
#{THIRD_VERSE_PART}
#{SECOND_VERSE_PART}
#{LAST_VERSE_PART}
EOS

  # Special Case Verse
  HORSE_VERSE = "I know an old lady who swallowed a horse.\nShe's dead, of course!\n"

  def get_template(verse_number)
    case (verse_number)
    when 1
      return FLY_TEMPLATE
    when 2
      return SECOND_VERSE_TEMPLATE
    when 3
      return THIRD_VERSE_TEMPLATE
    when 4
      return FOURTH_VERSE_TEMPLATE
    when 5
      return FIFTH_VERSE_TEMPLATE
    when 6
      return SIXTH_VERSE_TEMPLATE
    when 7
      return SEVENTH_VERSE_TEMPLATE
    end
  end
end

class FoodChainSong
  include FoodChainSongTemplate

  def verse(beginning_verse)
    fail ArgumentError, 'Verse number must be between 1 and 8', caller unless ANIMAL_HIERARCHY.include?(beginning_verse)

    if beginning_verse == 8
      return HORSE_VERSE
    else
      @first_animal_swallowed = beginning_verse
      generate_song(get_template(beginning_verse), binding)
    end
  end

  def verses(starting_verse, ending_verse)
    fail ArgumentError, 'Range given not of 1 to 8', caller if not_in_range?(ANIMAL_HIERARCHY, starting_verse, ending_verse)
    @combined_verse = ''

    (starting_verse..ending_verse).each do |verse_number|
      @combined_verse << verse(verse_number) + "\n"
    end

    @combined_verse
  end

  def sing
    verses(1, 8)
  end

  private

  def not_in_range?(hash, beginning, ending)
    !(hash.include?(beginning) && hash.include?(ending))
  end

  def generate_song(template, context)
    ERB.new(template).result(context)
  end
end
