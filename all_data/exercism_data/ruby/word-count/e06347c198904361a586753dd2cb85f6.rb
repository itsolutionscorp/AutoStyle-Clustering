class Phrase

  def initialize phrase
    words = phrase.downcase.split(/[\W_]+/)
    @values = words.each_with_object(Hash.new()) do |word, values|
      values[word] ||= 0
      values[word] += 1
    end
  end

  def word_count
    @values
  end

end
