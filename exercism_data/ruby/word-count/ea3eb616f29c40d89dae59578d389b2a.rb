class Phrase < String

  def word_count
    words.inject(Hash.new(0)) do |hash,word|
      hash[word] += 1
      hash
    end
  end

  def words
    scan(/\w+/).map{|w| w.downcase }
  end

end
