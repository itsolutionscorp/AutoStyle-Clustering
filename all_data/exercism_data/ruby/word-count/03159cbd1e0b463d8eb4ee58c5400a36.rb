Phrase = Struct.new(:phrase) do

  def word_count
    count(words)
  end

  private
    def words
      phrase.downcase.scan(/\w+/)
    end

    def count(words) 
      words.each_with_object(Hash.new(0)) {|w, h| h[w] += 1 }
    end
end
