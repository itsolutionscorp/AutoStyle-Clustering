class Phrase

  def initialize(input)
    @sentence = input
  end

  def word_count
    result_hash = Hash.new(0)
    @sentence.scan(/\b[a-zA-Z1-9]{1,}/) { |word| result_hash[word.downcase] += 1 }
    result_hash
  end

end
