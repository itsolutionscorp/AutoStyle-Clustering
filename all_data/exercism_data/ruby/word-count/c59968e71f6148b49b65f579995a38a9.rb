class Words
  def initialize(sentence)
    @sentence = sentence
  end

  def count
    counter = Hash.new(0)
    words.each do |word|
      counter[word] += 1
    end
    counter
  end

  private

  def words
    @sentence.scan(/\w+/).map(&:downcase)
  end
end
