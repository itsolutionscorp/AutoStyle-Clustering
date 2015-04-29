class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    counts = Hash.new(0)
    words = prepare_text_for_counting

    words.each do |word|
      counts[word] += 1
    end

    counts
  end

  private

  def prepare_text_for_counting
    p = @text.downcase.split(/[\W]+/)
  end
end
