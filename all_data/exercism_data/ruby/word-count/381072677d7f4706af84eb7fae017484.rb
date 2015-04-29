class Words

  def initialize words
    @words = parse words
  end

  def count
    @words.each_with_object({}) do |word, result|
      result[word] = 0 unless result[word]
      result[word] += 1
    end
  end

  private

  def parse words
    scrub(words).split
  end

  def scrub words
    words.downcase.gsub(/[^\w\s]/, "")
  end

end
