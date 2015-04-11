class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    @_word_count ||= Word::Count.new(word_list).count
  end

  def word_list
    @_word_list ||= @phrase.downcase.scan(/\w+/)
  end

  class Word
    class Count

      def initialize list
        @list = list
      end

      def count
        @_count ||= count_words
      end

      private

        def count_words
          @list.uniq.each_with_object({}) do |word,counter|
            counter[word] = @list.count(word)
          end
        end

    end
  end

end
