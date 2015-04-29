class Anagram
    attr_reader :base

    def initialize base
        @base = AnagramEngine.new(base)
    end

    def match possibilty
        possibilty.select do |w|
            base.anagramOf? w
        end
    end
end
class AnagramEngine
    attr_reader :word

    def initialize word
        @word = word
    end

    def anagramOf? sub
        !duplicate?(sub) && fingerprint == canonicalize(sub)

    end

    def duplicate? sub
        sub.downcase == word.downcase
    end

    def canonicalize sub
        sub.downcase.chars.sort
    end
    def fingerprint
        @fingerprint ||= canonicalize(word)
    end
end
