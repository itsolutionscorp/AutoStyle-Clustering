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
    Hash.new { |h,k| h[k] = 0 }.tap do |counts|
      words.each do |word|
        counts[normalize(word)] += 1
      end
    end
  end

  def normalize(word)
    word.downcase
  end

  def words
    contents.to_s.scan(/\w+/)
  end
end
