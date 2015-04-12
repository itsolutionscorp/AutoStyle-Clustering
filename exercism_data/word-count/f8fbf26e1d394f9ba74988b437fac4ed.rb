Phrase = Struct.new(:phrase) do

  def word_count
    histogram(normalize)
  end

  private
    def normalize
      phrase.downcase.scan(/\w+/)
    end

    def histogram(words) 
      words.each_with_object(Hash.new(0)) {|w, h| h[w] += 1 }
    end
end