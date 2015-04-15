class Phrase
  module Word
    def clean
      gsub /\W/, ""
    end
  end


  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    counts = Hash.new
    counts.default = 0

    extract_words.each_with_object(counts) do |word, counts|
      counts[word] += 1
    end
  end

  private

  def extract_words
    parts = @phrase.split(" ").each do |word|
      word.extend Word
    end

    parts.map(&:clean).reject &:empty?
  end
end
