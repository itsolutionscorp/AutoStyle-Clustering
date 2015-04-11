require 'pry'
class Phrase


  def initialize(phrase)
    @words = phrase
  end

  def word_count
    counts = Hash.new(0)
    words2 = @words.downcase.split(/[ :,.!&@$%^&]+/ )
    words2.each do |word|
      counts[word] += 1
    end
    counts
  end
end
