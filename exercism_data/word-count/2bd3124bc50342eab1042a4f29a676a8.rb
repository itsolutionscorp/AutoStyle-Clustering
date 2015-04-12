class Words
  def initialize(text)
    @text = text
  end

  def count
    occurrence_count(word_list(@text))
  end

  private

  def word_list(text)
    text.downcase.split(/[^\w]+/)
  end

  def occurrence_count(list)
    list.inject(Hash.new(0)) {|counts, word|
      counts[word] += 1
      counts
    }
  end
end
