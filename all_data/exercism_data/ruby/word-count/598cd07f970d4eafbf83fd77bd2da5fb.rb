class Phrase 
  def initialize input
    @phrase = input
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1 
    end
  end

  private

  def words
    @phrase.downcase.gsub(/\W+/, ' ').split(" ")
  end
end
