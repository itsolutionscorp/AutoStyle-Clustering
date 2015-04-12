class Words
  attr_reader :count

  def initialize(in_string)
    @count = count_pairs(in_string)
  end

  private

  def count_pairs(in_string)
    results = Hash.new(0)

    list_of_words(in_string).each {|word| results[word] += 1}
    results
  end

  def list_of_words(in_string)
    normalize(in_string).split
  end

  def normalize(text)
    text.gsub(/\W/, ' ').downcase
  end
end
