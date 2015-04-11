class FoodChainSong
  def sing
    verses(Swallowable.min, Swallowable.max)
  end

  def verses(first, last)
    first.upto(last).map { |n| verse(n) }.join("\n") + "\n"
  end

  def verse(number)
    Swallowable.at(number).to_s + "\n"
  end
end


class FoodChainSong
  class Swallowable
    def self.at(index)
      all[index]
    end

    def self.min
      all.keys.first
    end

    def self.max
      all.keys.last
    end

    def self.all
      @all ||= {
        1 => Swallowable.new("fly",    aside: "I don't know why she swallowed the fly. Perhaps she'll die."),
        2 => Swallowable.new("spider", aside: "It wriggled and jiggled and tickled inside her.", qualified: "spider that wriggled and jiggled and tickled inside her"),
        3 => Swallowable.new("bird",   aside: "How absurd to swallow a bird!"),
        4 => Swallowable.new("cat",    aside: "Imagine that, to swallow a cat!"),
        5 => Swallowable.new("dog",    aside: "What a hog, to swallow a dog!"),
        6 => Swallowable.new("goat",   aside: "Just opened her throat and swallowed a goat!"),
        7 => Swallowable.new("cow",    aside: "I don't know how she swallowed a cow!"),
        8 => Swallowable.new("horse",  aside: "She's dead, of course!", lethal: true),
      }
    end

    attr_reader :object, :aside, :previous_object

    def initialize(object, aside:, qualified: nil, lethal: false)
      @object, @aside, @qualified_object, @lethal = object, aside, qualified, lethal
    end

    def to_s
      (latest_swallowing + earlier_swallowings).join("\n")
    end

    def reason_or_aside
      if pred
        "She swallowed the #{object} to catch the #{pred.qualified_object}."
      else
        aside
      end
    end

    def qualified_object
      @qualified_object || object
    end

    private

    def latest_swallowing
      [swallowing, aside]
    end

    def swallowing
      "I know an old lady who swallowed a #{object}."
    end

    def earlier_swallowings
      if !pred || lethal?
        []
      else
        number.downto(self.class.min).map { |i| self.class.at(i).reason_or_aside }
      end
    end

    def number
      self.class.all.values.index(self) + 1
    end

    def pred
      number == 1 ? nil : self.class.all.values[number - 2]
    end

    def lethal?
      @lethal
    end
  end
end
