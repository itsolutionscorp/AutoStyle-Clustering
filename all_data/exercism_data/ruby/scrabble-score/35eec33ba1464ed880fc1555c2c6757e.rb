class Scrabble
    attr_reader :word, :letters, :score

    @@value_of = {
        "a" => 1, "b" => 3, "c" =>  3, "d" => 2, "e" =>  1, "f" => 4, "g" => 2,
        "h" => 4, "i" => 1, "j" =>  8, "k" => 5, "l" =>  1, "m" => 3, "n" => 1,
        "o" => 1, "p" => 3, "q" => 10, "r" => 1, "s" =>  1, "t" => 1, "u" => 1,
        "v" => 4, "w" => 4, "x" =>  8, "y" => 4, "z" => 10
    }

    def initialize(word)
        @word = word
        @letters = Scrabble.get_letters(word)
        @score = Scrabble.make_score(@letters)
    end

    def self.score(word)
        make_score get_letters word
    end

    private

    def self.get_letters(word)
        letter_matches = word && word.downcase.match(/[a-z]+/) || [""]
        letter_matches[0].strip.chars
    end

    def self.make_score(letters)
        letters.reduce(0) do |sum, letter|
            sum += @@value_of[letter]
        end
    end
end
