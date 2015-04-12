class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    counts = Hash.new(0)
    words = prepared_text

    words.each do |word|
      counts[word] += 1
    end

    counts
  end

  private

  def prepared_text
    @text.downcase.split(/\W+/)
  end
end
