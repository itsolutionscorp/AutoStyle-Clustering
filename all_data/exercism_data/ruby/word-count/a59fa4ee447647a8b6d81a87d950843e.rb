class Phrase
  def initialize(string)
    @phrase = string
  end

  def word_count
    phrase_as_array.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  def phrase_as_array
    @phrase.scan(/\w+/).map(&:downcase)
  end
end
