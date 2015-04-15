class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
    @word_count ||= words.inject(Hash.new(0)) do |counter, word|
      counter[word] += 1
      counter
    end
  end

  def words
    @str.gsub(/\W/, ' ').downcase.split(/\s+/)
  end
end
