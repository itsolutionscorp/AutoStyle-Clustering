class Phrase

  def initialize(text)
    @text = text
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end

  private

    def word_list
      text_with_punctuation_removed = text.gsub /[^a-zA-Z0-9 ,]/, ""
      text_with_normalized_separators = text_with_punctuation_removed.gsub " ", ","
      text_with_normalized_separators.split(",").select {|n| !n.empty? }
    end

    attr_reader :text

end
