class FoodChainSong

  def verse n
    raise ArgumentError unless (1..8).include? n

    animal = ANIMALS[n-1]
    premisces = PREMISCES[animal].to_s + "\n" unless animal == 'fly'
    explanations = EXPLANATIONS[0..(n-1)].reverse.join("\n") + "\n" unless animal == 'horse'

    "I know an old lady who swallowed a #{animal}.\n" +
       "#{premisces}" +
       "#{explanations}"
  end

  def verses n, m
    (n..m).map { |i| verse i }.join("\n") + "\n"
  end

  def sing
    verses 1, 8
  end

  ANIMALS = %w(fly spider bird cat dog goat cow horse)

  PREMISCES = {
    'fly' => nil,
    'spider' => "It wriggled and jiggled and tickled inside her.",
    'bird' => "How absurd to swallow a bird!",
    'cat' => "Imagine that, to swallow a cat!",
    'dog' => "What a hog, to swallow a dog!",
    'goat' => "Just opened her throat and swallowed a goat!",
    'cow' => "I don't know how she swallowed a cow!",
    'horse' => "She's dead, of course!"
  }

  EXPLANATIONS = [
    "I don't know why she swallowed the fly. Perhaps she'll die.",
    "She swallowed the spider to catch the fly.",
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    "She swallowed the cat to catch the bird.",
    "She swallowed the dog to catch the cat.",
    "She swallowed the goat to catch the dog.",
    "She swallowed the cow to catch the goat."
  ]

end
