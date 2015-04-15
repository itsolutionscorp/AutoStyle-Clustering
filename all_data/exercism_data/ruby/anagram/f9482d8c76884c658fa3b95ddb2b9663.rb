class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    matches = list.map { |word| word.downcase } & anagrams

    list.select { |word| matches.include? word.downcase }
  end

  private

  def letters
    @letters ||= @word.scan(/./).map { |c| c.downcase }
  end

  def anagrams
    @anagrams ||= letters
      .permutation(letters.count)
      .entries
      .map { |x| x.join }
      .uniq
      .reject { |word| word == @word }
  end

end
