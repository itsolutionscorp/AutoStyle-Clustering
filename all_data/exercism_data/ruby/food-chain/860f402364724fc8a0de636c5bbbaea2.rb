class FoodChainSong
  FIRST_LINE = "I know an old lady who swallowed a"
  VERSES = {
                   1 => {animal: "fly", rhyme: "I don't know why she swallowed the fly. Perhaps she'll die."},
                   2 => {animal: "spider", rhyme: "It wriggled and jiggled and tickled inside her.", repeat: " that wriggled and jiggled and tickled inside her"},
                   3 => {animal: "bird", rhyme: "How absurd to swallow a bird!"},
                   4 => {animal: "cat", rhyme: "Imagine that, to swallow a cat!"},
                   5 => {animal: "dog", rhyme: "What a hog, to swallow a dog!"},
                   6 => {animal: "goat", rhyme: "Just opened her throat and swallowed a goat!"},
                   7 => {animal: "cow", rhyme: "I don't know how she swallowed a cow!"},
                   8 => {animal: "horse", rhyme: "She's dead, of course!"}
           }
  # VERSES = VERSE_PARAMS.map { |params| FoodChainVerse.new(params) }

  def verses(start, stop=0)
    stop = start if stop < 1
    verses = []
    (start..stop).each { |n| verses << verse(n)}
    verses.join("\n") + "\n"
  end

  def verse(n)
    animal = VERSES[n][:animal]
    rhyme = VERSES[n][:rhyme]
    verse = "#{FIRST_LINE} #{animal}.\n#{rhyme}\n"
    return verse unless n.between?(2, 7)
    verse + refrain(n) + VERSES[1][:rhyme] + "\n"
  end

  def sing
    verses(1, 8)
  end

  private

  def refrain(n)
    animal1 = VERSES[n][:animal]
    animal2 = VERSES[n-1][:animal]
    repeat = VERSES[n-1][:repeat] || ""
    refrain = "She swallowed the #{animal1} to catch the #{animal2}#{repeat}.\n"
    return refrain if n < 3
    refrain + refrain(n-1)
  end
end

class FoodChainVerse
  def initialize(params)
    # @number = params[:number]
    @animal = params[:animal]
    @rhyme = params[:rhyme]
    @repeat = params[:repeat]
  end
end
