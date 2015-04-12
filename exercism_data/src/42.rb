class Phrase
  def initialize(sentence)
    @words = sentence.to_str.downcase.split(/\s|,/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counts|
      clean_word = word.gsub(/[^'\w]/, '')
      counts[clean_word] += 1 unless clean_word.empty?
    end
  end
end
