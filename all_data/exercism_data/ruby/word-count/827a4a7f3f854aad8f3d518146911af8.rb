class Phrase
  attr_reader :sentance

  def initialize(sentance)
    @sentance = sentance
  end

  def word_count
    results = Hash.new{|hash, key| hash[key] = 0}
    filtered_sentance = sentance.gsub(/\W/, " ").dup

    filtered_sentance.split(" ").map(&:downcase).each do |word|
      results[word] = results[word] + 1
    end
    results
  end

end
