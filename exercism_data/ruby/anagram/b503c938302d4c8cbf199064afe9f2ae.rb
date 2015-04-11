class Anagram
  def initialize(target)
    @target = target.downcase
  end

  def match(prospectives)
    prospectives.reject do |word|
      downcased_word = word.downcase
      downcased_word == @target || word.length != @target.length ||
        downcased_word.chars.sort != @target.chars.sort
    end
  end
end
