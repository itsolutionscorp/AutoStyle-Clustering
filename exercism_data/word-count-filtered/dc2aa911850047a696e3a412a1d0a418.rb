require 'pry'
class Phrase
  def initialize(str)
    @words = str.split(/[^a-zA-Z0-9\']+/).compact
  end

  def word_count
    {}.tap do |hash|
      @words.group_by(&:downcase).each do |word, occurrences|
        hash[word] = occurrences.length
      end
    end
  end
end
