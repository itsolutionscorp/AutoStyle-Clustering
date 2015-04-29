class Phrase
  def initialize(text)
    @text = text.to_s
    @words = @text.scan(/\w+/).map(&:downcase)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
