require 'set'


class Anagram

    def initialize(w)
        @w = w.downcase
    end

    def match(words)
        wordchars = @w.chars.sort
        words.each_with_object([]) { |w, l|
            if w.downcase.chars.sort == wordchars && w.downcase != @w then l.push(w) end
        }
    end

    def sort
        match.sort()
    end

end
