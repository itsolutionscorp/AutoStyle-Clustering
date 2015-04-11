class Phrase

  def initialize(input)
    @count_hash = clean_and_count(input)
  end

  def clean_and_count(input)
    words = input.scan(/\w+/)
    words.each_with_object(Hash.new(0)) do |word,hash|
      hash[word.downcase]+=1
    end
  end

  def word_count
    @count_hash
  end

end
