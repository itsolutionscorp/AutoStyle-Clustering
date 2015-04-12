class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_map = { }
    @phrase.split(/[\s,]+/).each do |word|
      normalize!(word)
      unless word.empty?
        word_map[word].nil? ? word_map[word] = 1 : word_map[word] += 1
      end
    end
    word_map
  end

  private

  def normalize!(word)
    word.gsub!(/[^a-zA-Z0-9']/,'')
    word.downcase!
  end
end
