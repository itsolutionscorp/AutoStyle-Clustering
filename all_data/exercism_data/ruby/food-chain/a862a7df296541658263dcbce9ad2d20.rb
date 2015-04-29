class FoodChainSong
  def verse(vnum)
    song_parts = [['fly', "I don't know why she swallowed the fly. Perhaps she'll die.\n"], 
                  ['spider', "It wriggled and jiggled and tickled inside her.\n"], 
                  ['bird', "How absurd to swallow a bird!\n"], 
                  ['cat', "Imagine that, to swallow a cat!\n"],
                  ['dog', "What a hog, to swallow a dog!\n"],
                  ['goat', "Just opened her throat and swallowed a goat!\n"],
                  ['cow', "I don't know how she swallowed a cow!\n"],
                  ['horse', "She's dead, of course!\n"]];
    verse_return = "I know an old lady who swallowed a #{song_parts[vnum - 1][0]}.\n#{song_parts[vnum - 1][1]}"
    if vnum > 1 && vnum < 8
      vnum.downto(2).each {|x|
        verse_return << "She swallowed the #{song_parts[x-1][0]} to catch the #{song_parts[x-2][0]}"
        verse_return << " that wriggled and jiggled and tickled inside her" if x == 3
        verse_return << ".\n"
        verse_return << song_parts[0][1] if x == 2
      }
    end
    verse_return
  end
  def verses(n1, n2)
    result = ''
    (n1..n2).each {|x| result << verse(x) << "\n"}
    result
  end
  def sing
    verses(1, 8)
  end
end
