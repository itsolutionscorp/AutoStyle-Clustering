#!/usr/bin/env ruby

# Exercism 13
# Food Chain Song

class FoodChainSong

  def initialize
    @lines = {  1 => { 1 => "I know an old lady who swallowed a fly.\n",
                       last: "I don't know why she swallowed the fly. Perhaps she'll die.\n" },
                2 => { 1 => "I know an old lady who swallowed a spider.\n",
                       2 => "It wriggled and jiggled and tickled inside her.\n",
                       last: "She swallowed the spider to catch the fly.\n" },
                3 => { 1 => "I know an old lady who swallowed a bird.\n",
                       2 => "How absurd to swallow a bird!\n",
                       last: "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" },
                4 => { 1 => "I know an old lady who swallowed a cat.\n",
                       2 => "Imagine that, to swallow a cat!\n",
                       last: "She swallowed the cat to catch the bird.\n" },
                5 => { 1 => "I know an old lady who swallowed a dog.\n",
                       2 => "What a hog, to swallow a dog!\n",
                       last: "She swallowed the dog to catch the cat.\n" },
                6 => { 1 => "I know an old lady who swallowed a goat.\n",
                       2 => "Just opened her throat and swallowed a goat!\n",
                       last: "She swallowed the goat to catch the dog.\n" },
                7 => { 1 => "I know an old lady who swallowed a cow.\n",
                       2 => "I don't know how she swallowed a cow!\n",
                       last: "She swallowed the cow to catch the goat.\n" },
                8 => { 1 => "I know an old lady who swallowed a horse.\n",
                       last: "She's dead, of course!\n" } }
  end

  def sing
    verses(1,8)
  end

  def verse(num)
    song = ''
    if num == 8 
      @lines[num].values.each { |x| song << x }
    else
      song << @lines[num].reject { |x, y| x == :last }.values.join
      num.downto(1) { |x| song << verse_end(x) }
    end
    song
  end

  def verse_end(num)
    @lines[num][:last]
  end

  def verses(from_verse, to_verse)
    song = ''
    (from_verse..to_verse).each { |x| song << verse(x) + "\n" }
    song
  end

end

z = ::FoodChainSong.new

p z.verses(1,3)
