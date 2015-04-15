class Phrase

  attr_reader :words

  def initialize(str)
    @words = parse str
  end

  def word_count
    occurrences = Hash.new(0)
    @words.each_with_object(occurrences) do |word, dict|
      dict[word] += 1
    end

    occurrences
  end

  private

  def parse(str)
    words_and_spaces = str.gsub /[^\w\s]/, ''

    words_and_spaces.downcase.split " "
  end

end
