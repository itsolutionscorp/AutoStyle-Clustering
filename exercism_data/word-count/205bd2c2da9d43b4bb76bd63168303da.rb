class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def words
   words = @phrase.downcase.split(/\W+/)
  end

   def word_count
     counts = Hash.new(0)
     words.each do |word|
       counts[word] += 1
     end
     counts
   end



end
