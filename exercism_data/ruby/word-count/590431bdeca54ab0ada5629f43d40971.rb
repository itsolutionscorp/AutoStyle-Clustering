class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    count(downcased_words)
  end

  private

    def count(enumerable)
      enumerable.each_with_object(Hash.new(0)) \
      do |element, counter|
        counter[element] += 1
      end
    end

    def downcased_words
      @text.downcase.scan(/\w+/)
    end
end