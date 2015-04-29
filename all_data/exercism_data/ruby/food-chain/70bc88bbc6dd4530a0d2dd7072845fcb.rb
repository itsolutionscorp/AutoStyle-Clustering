class FoodChainSong

  ANIMALS = [nil, :fly, :spider, :bird, :cat, :dog, :goat, :cow, :horse]
  VERSES  = {
              fly: "",
              spider: "It wriggled and jiggled and tickled inside her.\n",
              bird: "How absurd to swallow a bird!\n",
              cat: "Imagine that, to swallow a cat!\n",
              dog: "What a hog, to swallow a dog!\n",
              goat: "Just opened her throat and swallowed a goat!\n",
              cow: "I don't know how she swallowed a cow!\n",
              horse: "She's dead, of course!\n"
            }
  FINISH  = "I don't know why she swallowed the fly. Perhaps she'll die.\n"

  def verse n
    return start_song(ANIMALS[n]) if ANIMALS[n] == :horse

    start_song(ANIMALS[n]) <<
      n.downto(2).inject('') do |str,x|
        str + swallowed(x)
      end << FINISH
  end

  def verses start, fin
    (start..fin).each_with_object("") do |n,str|
      str << verse(n) << "\n"
    end
  end

  def sing
    verses(1,8)
  end

  private

    def start_song animal
      "I know an old lady who swallowed a #{animal}.\n" << VERSES[animal]
    end

    def swallowed n
      "She swallowed the #{ANIMALS[n]} to catch the #{ANIMALS[n-1]}#{spider_case if ANIMALS[n-1] == :spider}.\n"
    end

    def spider_case
      VERSES[:spider].sub("It", " that")[0..-3]
    end

end
