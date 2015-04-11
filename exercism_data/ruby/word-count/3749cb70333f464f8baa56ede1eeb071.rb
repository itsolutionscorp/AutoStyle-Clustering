class Phrase

  def initialize(text)
    @text = text
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

    def word_list
      text.scan(/\w+/).map(&:downcase)
    end

    attr_reader :text

end
