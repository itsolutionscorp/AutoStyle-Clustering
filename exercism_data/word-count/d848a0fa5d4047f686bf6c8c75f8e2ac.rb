class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    counts = Hash.new(0)
    words = prepare_for_counting(@text)

    words.each do |word|
      counts[word] += 1
    end

    counts
  end

  private

  def prepare_for_counting(text)
    p = text.downcase
    p.split(/[^\w]+/)
  end
end
