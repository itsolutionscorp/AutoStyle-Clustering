class Phrase

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    @word_count ||= process
  end

  private

  def process
    count = {}
    @sentence.split(/[_|,|:|\.|!|&|@|\$|%|\^|\s]+/).map(&:downcase).each do |word|
      count[word] = 0 unless count.has_key?(word)
      count[word] += 1
    end
    count
  end
end
