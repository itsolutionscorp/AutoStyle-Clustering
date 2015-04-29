class Anagram

  attr_reader :root

  def initialize(word)
    @root = Root.new word
  end

  def match(words)
    words.select do |word|
      Root.new(word) == root
    end
  end

  private

  class Root < String

    def initialize(word)
      super rootize(word)
    end

    private

    def rootize(word)
      sort word.downcase
    end

    def sort(word)
      word.chars.sort.join ''
    end

  end

end
