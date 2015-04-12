class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count =  Hash.new(0)
    count_words(phrase)
  end

  private
  attr_writer :word_count

  def count_words(text)
    text = sanitize_text(text)
    word_count = each_word(text)
  end

  def sanitize_text(text)
    text.gsub(/\W/, " ").downcase
  end

  def each_word(text)
    text.split(" ").each do |word|
      @word_count[word] += 1
    end
    @word_count
  end

end
