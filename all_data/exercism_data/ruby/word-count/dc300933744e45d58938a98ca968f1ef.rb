class Phrase

  def initialize(phrase)
    @words = get_words_from(phrase)
    @word_hash = {}
  end

  def word_count
    reset_word_count
    @words.each { |word| increment_word_count_for(word) }
    @word_hash
  end

  private
    def get_words_from(phrase)
      phrase.split(/[^\w]+/).each {|word| word.downcase!}
    end

    def reset_word_count
      @word_hash.clear      
    end

    def increment_word_count_for(word)
      @word_hash[word] = 0 unless @word_hash.has_key?(word)
      @word_hash[word] += 1
    end
end


# class Phrase

#   def initialize(phrase)
#     @words = get_words_from(phrase)
#   end

#   def word_count
#     word_hash = {}
#     @words.each do |word|
#       if word_hash.has_key?(word)
#         word_hash[word] += 1
#       else
#         word_hash[word] = 1
#       end
#     end
#     word_hash
#   end

#   private

#   def get_words_from(phrase)
#     phrase.split(/[^\w]+/).each {|word| word.downcase!}
#   end
# end
