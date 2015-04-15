class Phrase
  attr_reader :text

  def initialize(text)
    @text = String(text)
  end

  def word_count
    result = words.map do |word|
      [word, words.count(word)]
    end

    Hash[result]
  end

  def words
    @text.gsub(/[^\d\s\w]+/,'')
         .downcase
         .split(" ")
  end
end
