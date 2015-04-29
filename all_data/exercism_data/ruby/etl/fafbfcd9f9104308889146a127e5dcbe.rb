class ETL
  class << self

    attr_reader :new_format

    def transform(old_format)
      reset
      old_format.each do |point, words|
        set_words_with_point(words, point)
      end
      new_format
    end

    private

      def reset
        @new_format = {}
      end

      def set_words_with_point(words, point)
        words.each do |word|
          set_point(word, point)
        end
      end

      def set_point(word, point)
        new_format[word.downcase] = point
      end
  end
end
