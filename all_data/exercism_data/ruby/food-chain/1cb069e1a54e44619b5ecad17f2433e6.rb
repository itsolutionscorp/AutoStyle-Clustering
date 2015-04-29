require "yaml"

class FoodChainSong
  INTRO = [
           "She's dead, of course!",
           "I don't know how she swallowed a cow!",
           "Just opened her throat and swallowed a goat!",
           "What a hog, to swallow a dog!",
           "Imagine that, to swallow a cat!",
           "How absurd to swallow a bird!",
           "It wriggled and jiggled and tickled inside her.",
           nil,
          ]

  REASON = [
            "She swallowed the cow to catch the goat.",
            "She swallowed the goat to catch the dog.",
            "She swallowed the dog to catch the cat.",
            "She swallowed the cat to catch the bird.",
            "She swallowed the bird to catch the spider " +
              "that wriggled and jiggled and tickled inside her.",
            "She swallowed the spider to catch the fly.",
            "I don't know why she swallowed the fly. Perhaps she'll die.",
           ]

  ANIMAL = %w[horse cow goat dog cat bird spider fly]

  def verse n
    [
     "I know an old lady who swallowed a #{ANIMAL[-n]}.",
     INTRO[-n],
     (REASON.last(n) if n.between?(1, 7)),
     ""
    ].flatten.compact.join "\n"
  end

  def verses a, b
    (a..b).map { |n| verse n }.join("\n") +
      "\n" # wtf?
  end

  def sing
    verses 1, 8
  end
end
