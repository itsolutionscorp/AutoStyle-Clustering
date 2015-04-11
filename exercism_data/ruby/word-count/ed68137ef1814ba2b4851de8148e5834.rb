class Phrase

  def initialize value
    @value = value.to_s
  end

  def word_count
    count = Hash.new(0)
    split_words.each { |word| count[word.downcase] += 1 }
    count
  end

  private

    def split_words
      @value.split(/\W+/)
    end

end
