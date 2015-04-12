class Phrase
  attr_reader :str, :words

  def initialize(str)
    @str = str
    @words = str.downcase.split(/\s|,/)
      .map { |word| word.gsub(/[^\w\s']/, '') }
      .select { |word| !word.empty? }
  end

  def word_count
    words.each_with_object({}) do |word, counts|
      counts[word] ||= 0
      counts[word] += 1
    end
  end
end
