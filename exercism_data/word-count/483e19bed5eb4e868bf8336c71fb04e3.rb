class Phrase < Struct.new(:text)
  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def words
    text.downcase.scan(/['\w]+/)
  end
end
