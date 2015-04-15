class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    words.find_all do |word|
      other = Anagram.new(word)
      !same_word?(other) && matches?(other)
    end
  end

protected

  def letters
    normalize.chars.sort
  end

  def normalize
    @word.downcase
  end

private

  def matches?(other)
    other.letters == letters
  end

  def same_word?(other)
    other.normalize == normalize
  end

end
