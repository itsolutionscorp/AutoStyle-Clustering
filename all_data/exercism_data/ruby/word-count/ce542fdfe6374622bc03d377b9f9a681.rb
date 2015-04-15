class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordCounter.new.count(@phrase)
  end
end

class WordCounter
  def count(phrase)
    split_phrase(phrase).each_with_object(count_hash) do |word, memo|
      memo[word] += 1
    end
  end

  private
    
    def count_hash
      Hash.new { |h,k| h[k] = 0 }
    end

    def split_phrase(phrase)
      phrase.downcase.scan(/\w+/)
    end
end
