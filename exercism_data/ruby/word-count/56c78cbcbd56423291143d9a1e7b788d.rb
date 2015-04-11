class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_acc = @phrase.split.inject(Hash.new(0)) do |memo, word|
      word = word.gsub(/\W/, '').downcase
      memo[word] += 1 if word && word.length >= 1
      memo
    end
  end
end
