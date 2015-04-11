class Phrase

  def initialize text
    @text = text
  end

  def word_count
    words.inject(counter_hash) do |hash,word|
      hash[word] += 1
      hash
    end
  end

  private
    def counter_hash
      Hash.new { 0 }
    end

    def words
      lower_text.scan /\w+/
    end

    def lower_text
      @text.downcase
    end

end
