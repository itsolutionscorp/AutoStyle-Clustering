class Anagram
  def initialize(word)
    @word = word
    @character_count = count_characters(word)
  end

  def match(list)
    list.reject{|word|
      @word.downcase == word.downcase ||
      @character_count != count_characters(word)
    }
  end

  private
  def count_characters(word)
    Hash.new{|hash, key| hash[key] = 0}.tap do |hash|
      word.each_char do |c|
        hash[c.downcase] += 1
      end
    end
  end
end
