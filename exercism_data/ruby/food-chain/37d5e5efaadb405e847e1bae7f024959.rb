class FoodChainSong

  def verse n
    raise ArgumentError unless (1..8).include? n

    animals = (1..n).map { |i| Animal.new(i) }
    animal = animals.last
    explanations = animals.map { |a| a.explanation }.reverse.join("\n") + "\n" unless animal.last?

    # animal = ANIMALS[n-1]
    # premisces = PREMISCES[animal].to_s + "\n" unless animal == 'fly'
    # explanations = EXPLANATIONS[0..(n-1)].reverse.join("\n") + "\n" unless animal == 'horse'

    "I know an old lady who swallowed a #{animal.name}.\n" +
      "#{premisces.premisces}" +
      "#{explanations}"
  end

  def verses n, m
    (n..m).map { |i| verse i }.join("\n") + "\n"
  end

  def sing
    verses 1, 8
  end


end

class Animal

  attr_reader :name

  def initialize(n)
    @name = NAMES[n-1]
  end

  def premisce
    return PREMISCES[name] + "\n" if PREMISCES[name]
    ""
  end

  def explanation
    EXPLANATIONS[name]
  end

  def last?
    name == 'horse'
  end

  NAMES = %w(fly spider bird cat dog goat cow horse)

  PREMISCES = {
    'fly'    => nil,
    'spider' => "It wriggled and jiggled and tickled inside her.",
    'bird'   => "How absurd to swallow a bird!",
    'cat'    => "Imagine that, to swallow a cat!",
    'dog'    => "What a hog, to swallow a dog!",
    'goat'   => "Just opened her throat and swallowed a goat!",
    'cow'    => "I don't know how she swallowed a cow!",
    'horse'  => "She's dead, of course!"
  }

  EXPLANATIONS = [
    'fly'    => "I don't know why she swallowed the fly. Perhaps she'll die.",
    'spider' => "She swallowed the spider to catch the fly.",
    'bird'   => "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    'cat'    => "She swallowed the cat to catch the bird.",
    'dog'    => "She swallowed the dog to catch the cat.",
    'goat'   => "She swallowed the goat to catch the dog.",
    'cow'    => "She swallowed the cow to catch the goat."
  ]

end
