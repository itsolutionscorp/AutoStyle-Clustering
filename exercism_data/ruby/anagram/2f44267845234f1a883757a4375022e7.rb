class Anagram
    def initialize(word)
        @word = normalize(word)
    end

    def match(possible_anagrams)
        possible_anagrams.find_all { |w|
            anagram?(normalize(w))
        }
    end

    private
    def normalize(word)
        word.downcase
    end

    def anagram?(testword)
        @word != testword &&
            sorted_chars(@word) == sorted_chars(testword)
    end

    def sorted_chars(word)
        word.chars.sort
    end
end
