class Anagram
  attr_reader :root, :constituents

  def initialize(root)
    @root = root
    @constituents = WordConstituents.new(root)
  end
  
  def match(words)
    words.each_with_object([]) do |word, matches|
      next if identical?(word)
      if constituents.match?(word)
        matches << word
      end
    end
  end

  private

  def identical?(word)
    word.downcase == root    
  end

  class WordConstituents
    attr_reader :constituents

    def initialize(word)
      @constituents = create_histogram(word)
    end

    def match?(word)
      constituents == create_histogram(word)
    end

    private

    def create_histogram(word)
      letter_hist = Hash.new(0)
      word.downcase.scan(/\w/) do |l|
        letter_hist[l] += 1
      end
      letter_hist
    end
  end
end
