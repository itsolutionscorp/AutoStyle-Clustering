class Phrase < Struct.new(:phrase)
  def word_count
    counts = {}

    each_normalized_word do |word|
      counts[word] ||= 0
      counts[word] += 1
    end

    counts
  end

  private

  def each_normalized_word(&block)
    each_word do |word|
      block.call(normalize(word))
    end
  end

  def each_word(&block)
    phrase.scan(/\w+/, &block)
  end

  def normalize(word)
    word.downcase
  end
end
