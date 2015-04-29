class Phrase
  def initialize(words)
    @word_list = build_word_list(words)
  end

  def word_count
    counts = Hash.new(0)

    @word_list.each do |word|
      counts[word] += 1
    end

    counts
  end

  private
    def build_word_list(words)
      words.downcase.gsub(/[^0-9a-z ]/i, '').split(" ")
    end
end
