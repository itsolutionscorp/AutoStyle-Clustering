class Phrase

  def initialize value
    @value = value.to_s
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word.downcase] += 1
    end
  end

  private

    def words
      @words ||= @value.split(/\W+/)
    end

end
