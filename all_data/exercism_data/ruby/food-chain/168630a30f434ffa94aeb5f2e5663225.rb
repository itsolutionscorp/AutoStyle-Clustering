class FoodChainSong
  def initialize
  end

  def verse(n)
    [{name: 'horse',
      verse: "I know an old lady who swallowed a horse.\n" +
             "She's dead, of course!\n"},
     {name: 'cow',
      verse: "I know an old lady who swallowed a cow.\n" +
             "I don't know how she swallowed a cow!\n" +
             "She swallowed the cow to catch the goat.\n" +
             "She swallowed the goat to catch the dog.\n" +
             "She swallowed the dog to catch the cat.\n" +
             "She swallowed the cat to catch the bird.\n" +
             "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
             "She swallowed the spider to catch the fly.\n" +
             "I don't know why she swallowed the fly. " +
             "Perhaps she'll die.\n"},
     {name: 'goat',
      verse: "I know an old lady who swallowed a goat.\n" +
             "Just opened her throat and swallowed a goat!\n" +
             "She swallowed the goat to catch the dog.\n" +
             "She swallowed the dog to catch the cat.\n" +
             "She swallowed the cat to catch the bird.\n" +
             "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
             "She swallowed the spider to catch the fly.\n" +
             "I don't know why she swallowed the fly. " +
            "Perhaps she'll die.\n"},
     {name: 'dog',
      verse: "I know an old lady who swallowed a dog.\n" +
             "What a hog, to swallow a dog!\n" +
             "She swallowed the dog to catch the cat.\n" +
             "She swallowed the cat to catch the bird.\n" +
             "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
             "She swallowed the spider to catch the fly.\n" +
             "I don't know why she swallowed the fly. " +
             "Perhaps she'll die.\n"},
     {name: 'cat',
      verse: "I know an old lady who swallowed a cat.\n" +
             "Imagine that, to swallow a cat!\n" +
             "She swallowed the cat to catch the bird.\n" +
             "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
             "She swallowed the spider to catch the fly.\n" +
             "I don't know why she swallowed the fly. " +
             "Perhaps she'll die.\n"},
     {name: 'bird',
      verse: "I know an old lady who swallowed a bird.\n" +
            "How absurd to swallow a bird!\n" +
            "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
            "She swallowed the spider to catch the fly.\n" +
            "I don't know why she swallowed the fly. Perhaps she'll die.\n"},
     {name: 'spider',
      verse: "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" +
             "She swallowed the spider to catch the fly.\n" +
             "I don't know why she swallowed the fly. Perhaps she'll die.\n"},
     {name: 'fly',
      verse: "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n"}]
    .reverse[n-1][:verse]
  end

  def verses(m, n)
    (m..n).each_with_object('') do |i, a|
      a << "#{verse(i)}\n"
    end
  end

  def sing
    verses(1, 8)
  end
end
