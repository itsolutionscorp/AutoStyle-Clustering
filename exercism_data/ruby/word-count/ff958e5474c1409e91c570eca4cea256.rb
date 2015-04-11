class Phrase
  def initialize(text)
    @text = text.to_s
  end

  def ignore_case
    @text.downcase!
  end

  def split_words
    @text.scan(/\w+/)
  end

  def word_count
    ignore_case
    @words = split_words

    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
