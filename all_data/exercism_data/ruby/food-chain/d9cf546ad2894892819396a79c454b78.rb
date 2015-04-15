class FoodChainSong
  def sing
    verses(1, 8)
  end

  def verses(i, j)
    raise ArgumentError unless [i, j].all? { |n| (1..8).cover?(n) } and j > i

    (i..j).reduce("") { |result, i| result << verse(i) << "\n" }
  end

  def verse(i)
    raise ArgumentError unless (1..8).cover?(i)

    case i
    when 1
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a fly.
      I don't know why she swallowed the fly. Perhaps she'll die.
      eos
    when 2
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a spider.
      It wriggled and jiggled and tickled inside her.
      She swallowed the spider to catch the fly.
      I don't know why she swallowed the fly. Perhaps she'll die.
      eos
    when 3
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a bird.
      How absurd to swallow a bird!
      She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
      She swallowed the spider to catch the fly.
      I don't know why she swallowed the fly. Perhaps she'll die.
      eos
    when 4
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a cat.
      Imagine that, to swallow a cat!
      She swallowed the cat to catch the bird.
      She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
      She swallowed the spider to catch the fly.
      I don't know why she swallowed the fly. Perhaps she'll die.
      eos
    when 5
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a dog.
      What a hog, to swallow a dog!
      She swallowed the dog to catch the cat.
      She swallowed the cat to catch the bird.
      She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
      She swallowed the spider to catch the fly.
      I don't know why she swallowed the fly. Perhaps she'll die.
      eos
    when 6
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a goat.
      Just opened her throat and swallowed a goat!
      She swallowed the goat to catch the dog.
      She swallowed the dog to catch the cat.
      She swallowed the cat to catch the bird.
      She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
      She swallowed the spider to catch the fly.
      I don't know why she swallowed the fly. Perhaps she'll die.
      eos
    when 7
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a cow.
      I don't know how she swallowed a cow!
      She swallowed the cow to catch the goat.
      She swallowed the goat to catch the dog.
      She swallowed the dog to catch the cat.
      She swallowed the cat to catch the bird.
      She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
      She swallowed the spider to catch the fly.
      I don't know why she swallowed the fly. Perhaps she'll die.
      eos
    when 8
      <<-eos.gsub(/^\s+/, "")
      I know an old lady who swallowed a horse.
      She's dead, of course!
      eos
    end
  end
end
