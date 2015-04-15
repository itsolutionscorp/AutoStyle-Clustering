class FoodChainSong

  def verse n
    raise ArgumentError unless (1..8).include? n
    Animal.new(n).story
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

  def story
    "I know an old lady who swallowed a #{name}.\n" +
      "#{premisces}" +
      "#{explanations}"
  end

  private

  def premisces
    PREMISCES[name] + "\n" if PREMISCES[name]
  end

  def explanations
    id = NAMES.index(name)
    NAMES[0..id].map { |name| EXPLANATIONS[name] }.reverse.join("\n") + "\n" unless name == 'horse'
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

  EXPLANATIONS = {
    'fly'    => "I don't know why she swallowed the fly. Perhaps she'll die.",
    'spider' => "She swallowed the spider to catch the fly.",
    'bird'   => "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    'cat'    => "She swallowed the cat to catch the bird.",
    'dog'    => "She swallowed the dog to catch the cat.",
    'goat'   => "She swallowed the goat to catch the dog.",
    'cow'    => "She swallowed the cow to catch the goat."
  }

  private_constant :PREMISCES, :EXPLANATIONS, :NAMES

end
