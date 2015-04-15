class Phrase
  def initialize(sentence)
    @words = sentence.downcase.split(/\s|,/)
  end

  def word_count
    counts = Hash.new(0)

    @words.each do |word|
      clean_word = word.gsub(/[^'\w]/, '')
      counts[clean_word] += 1 unless clean_word.empty?
    end

    counts
  end
end
