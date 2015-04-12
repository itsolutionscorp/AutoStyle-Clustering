class Phrase
  def initialize(words)
    @word_list = build_word_list(words)
  end

  def word_count
    @word_list.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private
    def build_word_list(words)
      words.downcase.scan(/\w+/)
    end
end
