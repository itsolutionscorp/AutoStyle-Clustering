class Anagram
  def initialize(target)
    @target = target.downcase
  end

  def match(prospectives)
    prospectives.reject do |word|
      downcased_word = word.downcase
      downcased_word == @target || word.length != @target.length ||
        downcased_word.sorted_chars != @target.sorted_chars
    end
  end
end

class String
  def sorted_chars
    self.chars.sort
  end
end
