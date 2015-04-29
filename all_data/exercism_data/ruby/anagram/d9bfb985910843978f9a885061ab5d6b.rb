class String
  def to_array
    self.downcase.split("")
  end
end

class Anagram
  attr_accessor :word

  def initialize(word)
    @word=word.downcase
  end

  def match(array)
      matching_word_array=[]
      array.each do |match|
      matching_word_array << match if match.to_array.sort == word.to_array.sort && match.downcase != word
      end
      matching_word_array
  end
end
