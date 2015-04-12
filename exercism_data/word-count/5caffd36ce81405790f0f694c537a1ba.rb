class Phrase

  def initialize text
    @text = text
  end

  def word_count
    words.inject(counter_hash) { |hash,word| hash[word] += 1; hash }
  end

  private
    def counter_hash
      Hash.new { 0 }
    end

    def words
      stripped_text.downcase.split ' '
    end

    def stripped_text
      cleaned_text.squeeze(' ').strip
    end

    def cleaned_text
      lower_text.gsub %r/[^a-z0-9\s]+/, ' '
    end

    def lower_text
      @text.downcase
    end

end
