class Phrase
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    words = @sentence.gsub(/[^a-zA-Z0-9\ \']/, ' ').split(" ").map(&:downcase)
    count = Hash.new(0)
    words.each { |word| count[word] += 1 }
    count
  end
end
