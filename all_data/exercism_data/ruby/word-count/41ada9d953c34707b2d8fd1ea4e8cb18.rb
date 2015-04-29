class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def words
    @phrase.split(/\W+/)
  end

  def word_groups
    words.group_by {|w| w}
  end

  def word_count
    counts = {}
    
    word_groups.each do |word, word_group|
      counts[word] = word_group.count
    end
 
    counts
  end

end
