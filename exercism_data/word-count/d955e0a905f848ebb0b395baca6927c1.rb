class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word.downcase] += 1
    end
  end

  def words
    @str.scan(/\w+/)
  end
end
