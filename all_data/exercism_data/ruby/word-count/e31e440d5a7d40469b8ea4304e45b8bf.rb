class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    # more complex regex but less chaining-method, which is better?
    # @phrase.scan(/[a-z]+[\-\']?[a-z]*|[0-9]+/i).each_with_object...
    @phrase.gsub(/[^a-z0-9'-]/i, ' ').split(' ').each_with_object(Hash.new(0)) do |word, count|
      count[word.downcase] += 1
    end
  end
end
