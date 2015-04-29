class PigLatin
  def self.translate(string)
    string.split(" ").map {|word| WordTranslator.new(word).translate }.join(" ")
  end

  class WordTranslator

    # some of these are actually digraphs
    BLENDS = %w[bl cl fl gl pl br cr dr fr gr pr tr sk sl sp st sw spr str thr th sch ch squ qu wh]
    VOWEL_SOUNDS = %w[a e i o u xr yt]

    attr_reader :word
    def initialize(word)
      @word = word
    end

    def translate
      matching_methods.lazy.map {|sym| send sym }.detect {|x| x }
    end

    private

    def matching_methods
      [:vowels, :blends, :default]
    end

    def vowels
      if VOWEL_SOUNDS.any? {|d| word.start_with? d }
        "#{word}" << "ay"
      end
    end

    def blends
      if blend = BLENDS.detect {|d| word.start_with? d }
        "#{word[blend.length..-1]}#{blend}ay"
      end
    end

    def default
      "#{word[1..-1]}#{word[0]}ay"
    end

  end
end
