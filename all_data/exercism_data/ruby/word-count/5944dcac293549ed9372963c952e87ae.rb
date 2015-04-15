class Phrase

  def initialize(s)
    @phrase = s

  end

  def word_count

    counts = {}
    words = @phrase.downcase.scan /\w+/

    words.each do |word|
      counts[word] ||= 0
      counts[word] += 1
    end

    counts

  end

end
