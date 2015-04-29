class Phrase

  attr_reader :words

  def initialize(str)
    @words = parse str
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, dict|
      dict[word] += 1
    end
  end

  private

  def parse(str)
    words_and_spaces = str.gsub /[^\w\s]/, ''

    words_and_spaces.downcase.split " "
  end

end
