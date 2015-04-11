class Anagram
  attr_reader :word

  def initialize(word)
    @word = AnagramWord.new(word)
  end

  def match(list)
    list.select do |possible_anagram|
      word.anagram_of?(AnagramWord.new(possible_anagram))
    end
  end

end

class AnagramWord
  attr_reader :source

  def initialize(source)
    @source = source
  end

  def anagram_of?(other)
    if duplicate?(other)
      other_word = other.source.clone
      if source.length == other_word.length
        source.each_char {|c| other_word.sub!(/#{c}/i, '')}
      end
      other_word.empty?
    end
  end

  private

  def duplicate?(other)
    source.upcase != other.source.upcase
  end

end
