# = food_chain.rb - FoodChainSong API
#
# A library to create variations on the food chain song from json, until the old lady dies.

require 'json'

class FoodChainSong

  # Hey, look, Ruby has heredocs.
  # TODO: I did not find a way to create a heredoc where the hash formats were escaped without
  # declaring the heredoc with single quotes.

  SONG_CONFIG_JSON = <<-'EOS'
    {
      "first_line": "I know an old lady who swallowed a %{item_swallowed}.",
      "chained_line": "She swallowed the %{item_swallowed} to catch the %{previous_item_swallowed}.",
      "last_chained_line": "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      "end_line": "She's dead, of course!\n",
      "song_parts": [
        {
            "item_swallowed": "fly"
        },
        {
            "item_swallowed": "spider",
            "second_line": "It wriggled and jiggled and tickled inside her.",
            "chained_item_swallowed": "spider that wriggled and jiggled and tickled inside her"
        },
        {
            "item_swallowed": "bird",
            "second_line": "How absurd to swallow a bird!"
        },
        {
            "item_swallowed": "cat",
            "second_line": "Imagine that, to swallow a cat!"
        },
        {
            "item_swallowed": "dog",
            "second_line": "What a hog, to swallow a dog!"
        },
        {
            "item_swallowed": "goat",
            "second_line": "Just opened her throat and swallowed a goat!"
        },
        {
            "item_swallowed": "cow",
            "second_line": "I don't know how she swallowed a cow!"
        },
        {
            "item_swallowed": "horse"
        }
    ]}
  EOS

  # Note: I'm going to pretend we're getting the json song config from user input

  def initialize(json_song_config = SONG_CONFIG_JSON)
    config_hash = JSON.parse(json_song_config)

    @songPartFormatter = FoodChainSongPartFormatter.from_hash(config_hash)
    build_song_part_objects(config_hash["song_parts"])
  end

  # returns the entire song
  def sing
    verses(1, @song_parts.length)
  end

  # returns the song starting with the given verse and ending with the given verse
  def verses(first, last)
    first.upto(last).map do |verse|
      verse(verse) << "\n"
    end.join
  end

  # returns the given verse of the song
  def verse(verse_number)
    if verse_number == @song_parts.length
      @song_parts.last.render_last_line
    else
      build_chained_song(verse_number)
    end
  end

  private

  # Build the song for the provided verse.

  def build_chained_song(verse_number)
    build_chained_parts(verse_number - 1) << @songPartFormatter.last_chained_line
  end

  # Build the parts of the song that are chained together; e.g. "She swallowed the
  # dog to catch the cat."

  def build_chained_parts(requested_verse_number)
    if requested_verse_number == 0
      @song_parts[0].render_first_line
    else
      # the song works "backwards"; the first thing she eats is the last in the chained.
      # list of things. hence, we work through the array and then reverse it before
      # putting it all together.
      @song_parts[1..requested_verse_number].map.with_index do |song_part, index|
        build_song_part(song_part, index + 1, requested_verse_number)
      end.reverse.join
    end
  end

  # Build the individual song part for the requested verse. This puts together either
  # the verse part when the song part matches the requested verse, or adds in the
  # chained line for the song part if it's not the requested verse.
  #
  # TODO: The fact that this method takes in the verse number and requested verse seems
  # to indicate that it might make more sense inlined with the calling method...

  def build_song_part(song_part, verse_number, requested_verse_number)
    song = ""
    song << build_requested_verse_part(song_part) unless verse_number != requested_verse_number
    song << song_part.render_chained_line
  end

  # A song part that matches the requested verse is made of the first and second lines.

  def build_requested_verse_part(song_part)
    song_part.render_first_line << song_part.render_second_line
  end

  # Create the song part instances from the configuration data

  def build_song_part_objects(song_parts_array)
    @song_parts = song_parts_array.each_with_index.reduce([]) do |song_parts, (part_hash, index)|
      song_parts << new_song_part(song_parts, part_hash, index)
    end
  end

  def new_song_part(array, part_hash, index)
    previous_part = array[index - 1] unless index < 1
    FoodChainSongPart.new(@songPartFormatter, index, previous_part, part_hash)
  end
end

# == Helper class that stores the song line templates.
# This class exists because I TDD'd Ruby String templates based on hashes.
# The responsibility of this class is isolated to taking in strings and
# formatting them based on song_parts. It's used as a singleton in the
# library, as only one instance of each string template is required.
class FoodChainSongPartFormatter

  def self.from_hash(config_hash)
    first_line = config_hash["first_line"]
    chained_line = config_hash["chained_line"]
    last_chained_line = config_hash["last_chained_line"]
    end_line = config_hash["end_line"]
    FoodChainSongPartFormatter.new(first_line, chained_line, last_chained_line, end_line)
  end

  attr_reader \
    :last_chained_line,
    :end_line,
    :chained_line

  def initialize(first_line, chained_line, last_chained_line, end_line)
    @first_line = first_line
    @chained_line = chained_line
    @last_chained_line = last_chained_line
    @end_line = end_line
  end

  def format_last_line(song_part)
    format_first_line(song_part) << end_line
  end

  def format_first_line(song_part)
    @first_line % song_part.to_hash << "\n"
  end

  def format_chained_line(song_part)
    # Todo: I didn't find a way to use an object for a String template, only hashes and arrays.
    # I would have guessed there's a way to do that...
    @chained_line % song_part.to_hash << "\n"
  end

end

# == Helper class that defines the song part
# This class exists to handle individual song part data, which this hands to the formatter's
# templates. These instances do not worry about how to build the structure of the song, they
# are capable of being any part of the song.
class FoodChainSongPart

  attr_accessor \
    :item_swallowed,
    :chained_item_swallowed

  def initialize(formatter, index, previous_part = nil, hash)
    @songPartFormatter = formatter
    @index = index
    @previous_part = previous_part
    hash.each do |key, value|
      instance_variable_set("@#{key}", value)
    end
  end

  def render_if_first_line
  end

  def render_second_line
    @second_line + "\n"
  end

  def render_chained_line
    @songPartFormatter.format_chained_line(self)
  end

  def render_first_line
    @songPartFormatter.format_first_line(self)
  end

  def render_last_line
    @songPartFormatter.format_last_line(self)
  end

  # Return a hash representation of this object, used to render the String templates.

  def to_hash
    hash = Hash.new { |hash, key| hash[key] = instance_variable_get("@#{key}") }
    instance_variables.each { |var| hash[var.to_s.delete("@").to_sym] }

    if !@previous_part.nil?
      chained_item_swallowed = @previous_part.chained_item_swallowed || @previous_part.item_swallowed
      hash[:previous_item_swallowed] = chained_item_swallowed
    end

    hash
  end

end




class ExtraTestCases

  def test_expanded_song
    other_json = <<-'EOS'
    {
      "first_line": "I know an old lady who swallowed a %{item_swallowed}.",
      "chained_line": "She swallowed the %{item_swallowed} to catch the %{previous_item_swallowed}.",
      "last_chained_line": "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      "end_line": "She's dead, of course!\n",
      "song_parts": [
        {
            "item_swallowed": "fly"
        },
        {
            "item_swallowed": "spider",
            "second_line": "It wriggled and jiggled and tickled inside her.",
            "chained_item_swallowed": "spider that wriggled and jiggled and tickled inside her"
        },
        {
            "item_swallowed": "hamster",
            "second_line": "It was not the answer to swallow a hamster!"
        },
        {
            "item_swallowed": "bird",
            "second_line": "How absurd to swallow a bird!"
        },
        {
            "item_swallowed": "cat",
            "second_line": "Imagine that, to swallow a cat!"
        },
        {
            "item_swallowed": "dog",
            "second_line": "What a hog, to swallow a dog!"
        },
        {
            "item_swallowed": "goat",
            "second_line": "Just opened her throat and swallowed a goat!"
        },
        {
            "item_swallowed": "cow",
            "second_line": "I don't know how she swallowed a cow!"
        },
        {
            "item_swallowed": "moose",
            "second_line": "Seriously, a MOOSE."
        },
        {
            "item_swallowed": "horse",
            "second_line": "Of course she swallowed a horse."
        },
        {
            "item_swallowed": "Godzilla."
        }
    ]}
    EOS
    @song = ::FoodChainSong.new(other_json)

    expected = "I know an old lady who swallowed a fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a spider.
It wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a hamster.
It was not the answer to swallow a hamster!
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a bird.
How absurd to swallow a bird!
She swallowed the bird to catch the hamster.
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a cat.
Imagine that, to swallow a cat!
She swallowed the cat to catch the bird.
She swallowed the bird to catch the hamster.
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a dog.
What a hog, to swallow a dog!
She swallowed the dog to catch the cat.
She swallowed the cat to catch the bird.
She swallowed the bird to catch the hamster.
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a goat.
Just opened her throat and swallowed a goat!
She swallowed the goat to catch the dog.
She swallowed the dog to catch the cat.
She swallowed the cat to catch the bird.
She swallowed the bird to catch the hamster.
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a cow.
I don't know how she swallowed a cow!
She swallowed the cow to catch the goat.
She swallowed the goat to catch the dog.
She swallowed the dog to catch the cat.
She swallowed the cat to catch the bird.
She swallowed the bird to catch the hamster.
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a moose.
Seriously, a MOOSE.
She swallowed the moose to catch the cow.
She swallowed the cow to catch the goat.
She swallowed the goat to catch the dog.
She swallowed the dog to catch the cat.
She swallowed the cat to catch the bird.
She swallowed the bird to catch the hamster.
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a horse.
Of course she swallowed a horse.
She swallowed the horse to catch the moose.
She swallowed the moose to catch the cow.
She swallowed the cow to catch the goat.
She swallowed the goat to catch the dog.
She swallowed the dog to catch the cat.
She swallowed the cat to catch the bird.
She swallowed the bird to catch the hamster.
She swallowed the hamster to catch the spider that wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
I don't know why she swallowed the fly. Perhaps she'll die.

I know an old lady who swallowed a Godzilla..
She's dead, of course!

"
    assert_equal expected, @song.sing

  end

end
