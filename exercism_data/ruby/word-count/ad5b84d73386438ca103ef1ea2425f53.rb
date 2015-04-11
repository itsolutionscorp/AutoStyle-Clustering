class Words

  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def count
    split.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1
    end
  end

  def split
    phrase.downcase.scan(%r{\w+})
  end
end
