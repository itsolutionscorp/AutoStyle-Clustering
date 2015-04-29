class Phrase


  def initialize(content)
    @count = calculate_word_count(content)
  end

  def word_count
    @count
  end

  private
  def calculate_word_count(content)
    words = content.downcase.split(/[\W]/).reject(&:empty?)
    uniq_words = words.uniq
    count = {}

    uniq_words.each do |word|
      count[word] = words.count(word)
    end
    count
  end
end
