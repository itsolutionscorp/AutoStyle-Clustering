class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    @word_count = Hash.new
    get_word_count(phrase)
  end

    def get_word_count(phrase)
      words = phrase.split(/\W+/)
      count_instances_of_words(words)
    end

    def count_instances_of_words(words)
      words.each do |word|
        downcase_word = word.downcase
        if @word_count.key?(downcase_word)
          @word_count[downcase_word] += 1
        else
          @word_count[downcase_word] = 1
        end
      end
    end

end
