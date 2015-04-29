class FoodChainSong
  attr_reader :verseElements
  def initialize
    @verseElements = [["fly", "I don't know why she swallowed the fly. Perhaps she'll die."],
    ["spider", "It wriggled and jiggled and tickled inside her."],
    ['bird', 'How absurd to swallow a bird!'],
    ['cat', 'Imagine that, to swallow a cat!'],
    ['dog', 'What a hog, to swallow a dog!'],
    ['goat', 'Just opened her throat and swallowed a goat!'],
    ['cow', "I don't know how she swallowed a cow!"],
    ['horse', "She's dead, of course!"]]
    
  end
  
  def verse(n)
    poem = ""
    first = n
    while n > 0
      if n == first
        poem << "I know an old lady who swallowed a #{verseElements[n-1][0]}.\n#{verseElements[n-1][1]}\n"
        if n >= 8
          return poem
        end
      elsif n == 2
        poem << "She swallowed the #{verseElements[n][0]} to catch the #{verseElements[n-1][0]} that #{verseElements[n-1][1][/(?<=\s).*/]}\n"
      elsif n == 1
        poem << "She swallowed the #{verseElements[n][0]} to catch the #{verseElements[n-1][0]}.\n#{verseElements[n-1][1]}\n"
      else
        poem << "She swallowed the #{verseElements[n][0]} to catch the #{verseElements[n-1][0]}.\n"
      end
      n -= 1
    end
    poem
  end
  
  def verses(a, b)
    (a..b).inject("") {|poem, n| poem << verse(n) << "\n"}
  end
  
  def sing
    verses(1,8)
  end
end

g = FoodChainSong.new
puts g.verse(6)
