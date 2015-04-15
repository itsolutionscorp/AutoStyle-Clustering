class FoodChainSong
  def sing
    verses
  end
  
  def verses first=1, last=8
    first.upto(last).map{|number| verse number}.join("\n") + "\n"
  end
  
  def verse number
    fail ArgumentError unless valid_verse? number
    case number
    when 8
      "I know an old lady who swallowed a horse.\n"+
      "She's dead, of course!\n"
    when 1
      "I know an old lady who swallowed a fly.\n"+
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    else
      output = ""
      output << "I know an old lady who swallowed a #{SONG[number-1][0]}.\n"
      output << SONG[number-1][1] + "\n"
      output << SONG[1...number].reverse.map{|part| part[2]}.join("\n") + "\n"
      output << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end
  end
  
  SONG = [
    ["fly",nil,nil],
    [
      "spider",
      "It wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly."
    ],
    [
      "bird",
      "How absurd to swallow a bird!",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her."
    ],
    [
      "cat",
      "Imagine that, to swallow a cat!",
      "She swallowed the cat to catch the bird."
    ],
    [
      "dog",
      "What a hog, to swallow a dog!",
      "She swallowed the dog to catch the cat."
    ],
    [
      "goat",
      "Just opened her throat and swallowed a goat!",
      "She swallowed the goat to catch the dog."
    ],
    [
      "cow",
      "I don't know how she swallowed a cow!",
      "She swallowed the cow to catch the goat."
    ],
    ['horse',nil,nil]
  ]
  private
  def valid_verse? number
    number > 0 && number <= 8
  end
end
