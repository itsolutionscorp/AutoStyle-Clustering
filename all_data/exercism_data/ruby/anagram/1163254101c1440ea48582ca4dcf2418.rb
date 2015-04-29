class Anagram

  def initialize(word)
    @word = word
  end

  def match(list)
    remove_identicals_in(list).select{ |item| anagram?(item) }
  end

  private
    attr_accessor :word

    def anagram?(other_word)
      count_chars(word) == count_chars(other_word)
    end

    def remove_identicals_in(list)
      list.select{ |item| item.downcase != word.downcase }
    end

    def count_chars(word)
      map = Hash.new 0
      word.downcase.split('').each{ |char| map[char] += 1 }
      map
    end
end
