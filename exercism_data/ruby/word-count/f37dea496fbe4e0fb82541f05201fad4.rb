class Phrase

  attr_reader :contents

  def initialize(message)
    @contents = message
  end

  def word_count
    counts = Hash.new(0)
    clean_words.each do |word|
      counts[word] += 1
    end
    return counts
  end

  def clean_words
    contents.downcase.scan(/\w+/)
  end

end
