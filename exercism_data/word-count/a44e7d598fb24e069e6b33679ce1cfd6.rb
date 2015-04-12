class Phrase
  require 'set'

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words = homogenize.split
    words.to_set.each_with_object({}) do |word, counts|
      counts[word] = words.count word
    end
  end

  def homogenize
    @phrase.downcase.gsub(/[^\w\s]/, '')
  end
end
