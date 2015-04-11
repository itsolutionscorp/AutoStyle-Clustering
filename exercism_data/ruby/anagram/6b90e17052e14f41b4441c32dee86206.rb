class Anagram
  def initialize(target)
    @target = target.downcase
  end

  def match(prospectives)
    prospectives.reject do |word|
      word.downcase == @target || word.length != @target.length ||
        word.downcase.chars.sort != @target.chars.sort
    end
  end
end
