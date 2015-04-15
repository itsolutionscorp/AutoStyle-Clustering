class Phrase < Struct.new(:text)

  def word_count
    initial = Hash.new(0)
    words.each_with_object(initial) do |word, hash| 
      hash[word] += 1
    end
  end


  private

  def words
    text.downcase.scan(/\w+/)
  end
end
