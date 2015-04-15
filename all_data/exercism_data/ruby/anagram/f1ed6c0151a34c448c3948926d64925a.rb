Anagram = Struct.new(:word) do
  def match(words)
    words.select do |given_word|
      self == self.class.new(given_word)
    end
  end

  def ==(anagram)
    anagram.is_a?(self.class) && !same_word?(anagram) && same_letters?(anagram)
  end

  protected

  def same_letters?(anagram)
    letters.sort == anagram.letters.sort
  end

  def same_word?(anagram)
    letters == anagram.letters
  end

  def letters
    @letters ||= word.downcase.chars
  end
end
