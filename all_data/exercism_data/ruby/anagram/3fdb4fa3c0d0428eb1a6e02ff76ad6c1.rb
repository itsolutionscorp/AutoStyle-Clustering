class Anagram
  def initialize(sequence)
    @sequence = sequence.downcase
  end

  def match(words)
    words.select { |word| anagram?(word.downcase) }
  end

  private

  def anagram?(word)
    letters(word).eql? letters(@sequence)
  end
  
  protected
  def letters(word)
    Hash[word.chars.uniq.map { |ch| [ch, word.count(ch)] }]
  end
end
