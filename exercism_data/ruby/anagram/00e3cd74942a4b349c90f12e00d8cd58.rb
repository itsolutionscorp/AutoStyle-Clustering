class Anagram

  attr_reader :source
  def initialize(source)
    @source = source
  end

  def match(word_list)
    sorted_source = alphabatize(source)
    word_list.select do |target|
      sorted_source == alphabatize(target)
    end
  end

  private

    def alphabatize(word)
      word.downcase.chars.sort
    end

end
