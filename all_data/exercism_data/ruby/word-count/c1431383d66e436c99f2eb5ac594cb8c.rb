class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    Hash.new(0).tap do |result|
      matches = @sentence.scan(/[A-Za-z'0-9]+/)
      matches.each do |word|
        result[word.downcase] += 1 if !word.empty?
      end
    end
  end
end
