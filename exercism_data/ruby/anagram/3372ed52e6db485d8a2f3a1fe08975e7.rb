class Anagram
  def initialize(word)
    @symbols = Hash.new(0)
    @word = word
    word.downcase.each_char { |symbol| @symbols[symbol] += 1 }
  end

  def match(candidates_words)
    candidates_words.select { |word| anagram?(word.downcase) }
  end

  private
    def anagram?(word)
      return false if word == @word

      symbols_counter = @symbols.dup
      word.each_char do |symbol|
        if (!symbols_counter.has_key?(symbol)) || (symbols_counter[symbol] == 0)
          return false
        else
          symbols_counter[symbol] -= 1
        end
      end
      
      number_unused_symbols = symbols_counter.values.inject { |sum, num| sum + num }
      number_unused_symbols == 0
    end
end
