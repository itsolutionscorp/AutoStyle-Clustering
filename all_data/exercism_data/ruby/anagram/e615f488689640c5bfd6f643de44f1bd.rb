module Indexable
  def normalize
    self.downcase.chars.sort.join
  end
end

String.send :include, Indexable

class Anagram
    def initialize word
      @word = word.downcase
      @key = word.normalize
    end

    def match words
      words.find_all { |w| w.normalize == @key  }
           .reject   { |i| i.downcase  == @word }
    end

end
