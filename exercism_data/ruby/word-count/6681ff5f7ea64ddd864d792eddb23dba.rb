class Phrase
  attr_reader :word_count

  def initialize(content)
    @content    = content
    @word_count = {}

    set_word_count
  end

private

  def inc_word_counter(word)
    @word_count[word] = @word_count[word] + 1
  end

  def set_word_counter(word)
    @word_count[word] = 1
  end

  def set_word_count
    words.map do |word| 
      if word_exist? word
        inc_word_counter word
      else
        set_word_counter word
      end
    end
  end

  def word_exist?(word)
    @word_count.has_key? word
  end

  def words
    @content.split(/[^a-zA-Z0-9']+/).map { |word| word.downcase }
  end
end
