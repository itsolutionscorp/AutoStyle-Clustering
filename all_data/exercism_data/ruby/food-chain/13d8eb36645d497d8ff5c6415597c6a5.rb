require "yaml"

class FoodChainSong
  # Reimplementing __END__ because it will look for the test file's __END__ data otherwise.
  DATA = File.read(__FILE__)[/.*__END__\n(.+)/m, 1]
  VERSES = YAML.load(DATA)

  def sing
    verses(*VERSES.keys.minmax)
  end

  def verses(first, last)
    first.upto(last).map { |n| verse(n) }.join("\n") + "\n"
  end

  def verse(number)
    VERSES.fetch(number)
  end
end

__END__
1: |
  I know an old lady who swallowed a fly.
  I don't know why she swallowed the fly. Perhaps she'll die.
2: |
  I know an old lady who swallowed a spider.
  It wriggled and jiggled and tickled inside her.
  She swallowed the spider to catch the fly.
  I don't know why she swallowed the fly. Perhaps she'll die.
3: |
  I know an old lady who swallowed a bird.
  How absurd to swallow a bird!
  She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
  She swallowed the spider to catch the fly.
  I don't know why she swallowed the fly. Perhaps she'll die.
4: |
  I know an old lady who swallowed a cat.
  Imagine that, to swallow a cat!
  She swallowed the cat to catch the bird.
  She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
  She swallowed the spider to catch the fly.
  I don't know why she swallowed the fly. Perhaps she'll die.
5: |
  I know an old lady who swallowed a dog.
  What a hog, to swallow a dog!
  She swallowed the dog to catch the cat.
  She swallowed the cat to catch the bird.
  She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
  She swallowed the spider to catch the fly.
  I don't know why she swallowed the fly. Perhaps she'll die.
6: |
  I know an old lady who swallowed a goat.
  Just opened her throat and swallowed a goat!
  She swallowed the goat to catch the dog.
  She swallowed the dog to catch the cat.
  She swallowed the cat to catch the bird.
  She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
  She swallowed the spider to catch the fly.
  I don't know why she swallowed the fly. Perhaps she'll die.
7: |
  I know an old lady who swallowed a cow.
  I don't know how she swallowed a cow!
  She swallowed the cow to catch the goat.
  She swallowed the goat to catch the dog.
  She swallowed the dog to catch the cat.
  She swallowed the cat to catch the bird.
  She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
  She swallowed the spider to catch the fly.
  I don't know why she swallowed the fly. Perhaps she'll die.
8: |
  I know an old lady who swallowed a horse.
  She's dead, of course!
