class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
    @word_hash = Hash.new(0)
  end
  
  def word_count
    words_in(phrase).each { |word| increment_count_for(word) } if @word_hash.empty?
    @word_hash
  end

  def phrase=(phrase)
    @phrase = phrase
    @word_hash.clear
  end

  private

    def words_in(phrase)
      phrase.downcase.split(/[^\w]+/)
    end

    def increment_count_for(word)
      @word_hash[word] += 1
    end
end
