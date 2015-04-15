class FoodChainSong
  class << self
    def creature_hash
      {
        fly: {
          text: "I don't know why she swallowed the fly. Perhaps she'll die.\n"
        },
        spider: {
          text: "It wriggled and jiggled and tickled inside her.",
          flavour: " that wriggled and jiggled and tickled inside her",
          catches: :fly
        },
        bird: {
          text: "How absurd to swallow a bird!",
          catches: :spider
        },
        cat: {
          text: "Imagine that, to swallow a cat!",
          catches: :bird
        },
        dog: {
          text: "What a hog, to swallow a dog!",
          catches: :cat
        },
        goat: {
          text: "Just opened her throat and swallowed a goat!",
          catches: :dog
        },
        cow: {
          text: "I don't know how she swallowed a cow!",
          catches: :goat
        },
        horse: {text:"She's dead, of course!"}
      }
    end

    def creature
      creature_hash.keys
    end

    def chain(num)
      case num
        when -1
          nil
        when 0..5
          predator=creature[num+1]
          prey=creature_hash[predator][:catches]
          flavour=creature_hash[prey][:flavour]||''
          ["She swallowed the #{predator} to catch the #{prey}#{flavour}.",chain(num-1)||creature_hash[:fly][:text]].join("\n")
        else
          ''
      end
    end

    def verse(num)
      ["I know an old lady who swallowed a #{creature[num]}.",creature_hash[creature[num]][:text],chain(num-1)].compact.join("\n")
    end
  end

  def verse(verse)
    self.class.verse(verse-1)
  end

  def verses(from_verse,to_verse)
    (from_verse..to_verse).map{|x| verse(x)}.join("\n")+"\n"
  end

  def sing
    verses(1,8)
  end
end
