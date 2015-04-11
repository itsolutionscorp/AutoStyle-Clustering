class Phrase

  def initialize(input)
    @count_hash = clean_and_count(input)
  end

  def clean_and_count(input)
    input.scan(/\w+/).each_with_object(Hash.new(0)) { |word,hash| hash[word.downcase]+=1 }
  end

  def word_count
    @count_hash
  end

end
