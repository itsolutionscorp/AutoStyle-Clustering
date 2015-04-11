class Phrase
  attr_reader :words

  def initialize(words)
    @words = words.split(/\W/)
                  .map(&:downcase)
                  .reject(&:empty?)
  end

  def word_count
    words.group_by { |word| words.find { |el| el == word } }
         .map { |k,v| Hash[k, v.count] }
         .inject(&:merge)
  end
end
