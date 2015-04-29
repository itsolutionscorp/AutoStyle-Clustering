class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new
    @phrase.split(/:|\s|,/).each do |word|
      next if word == ''
      word = remove_punct(word).downcase
      count[word].nil? ? count[word] = 1 : count[word] += 1
    end
    count
  end

  private

  def remove_punct(word)
    word.gsub(/[^a-zA-Z0-9']*/,'')
  end
end
