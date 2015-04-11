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
    split_phrase(phrase).inject(count_hash) do |memo, word|
      memo[word] += 1
      memo
    end
  end

  private
    
    def count_hash
      Hash.new { |h,k| h[k] = 0 }
    end

    def split_phrase(phrase)
      phrase.downcase.gsub(/\W/, ' ').split(' ')
    end
end
