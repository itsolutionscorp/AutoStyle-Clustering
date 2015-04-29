class Phrase

  def initialize(input)
    @count_hash = clean_and_count(input)
  end

  def clean_and_count(input)
    get_words(input).each_with_object(Hash.new(0)) do |word,hash|
      hash[word.downcase]+=1
    end
  end

  def get_words(input)
    input.scan(/\w+/)
  end

  def word_count
    @count_hash
  end

end
