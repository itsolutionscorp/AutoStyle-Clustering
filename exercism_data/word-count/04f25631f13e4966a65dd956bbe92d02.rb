class Phrase
  attr_reader :contents

  def initialize(contents)
    @contents = contents
  end

  def word_count
    @word_count ||= count_words
  end

  private

  def count_words
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[normalize(word)] += 1
    end
  end

  def normalize(word)
    word.downcase
  end

  def words
    contents.to_s.scan(/\w+/)
  end
end
