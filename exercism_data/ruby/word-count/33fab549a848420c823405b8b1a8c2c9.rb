class Words
  attr_accessor :count

  def initialize words
    parse words
  end

  private

  def parse words
    @count = {}
    words = scrub words
    words.split.each_with_object(@count) do |word, result|
      result[word] = 0 unless result[word]
      result[word] += 1
    end
  end

  def scrub words
    words.downcase.gsub(/[^\w\s]/, "")
  end
end
