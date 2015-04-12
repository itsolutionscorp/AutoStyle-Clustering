class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_hash = {}
    @phrase.downcase.gsub(/[^a-z0-9\s']/i, ' ').split(' ').each do |word|
      if count_hash[word] == nil
        count_hash[word] = 1
      else 
        count_hash[word] += 1
      end
    end
    count_hash
  end

end
