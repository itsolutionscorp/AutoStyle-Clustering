class Phrase
  def initialize phrase
    @phrase = phrase.downcase.gsub(/[^a-zA-Z0-9']+/, ' ').strip
  end

  def word_count
    Hash[@phrase.split(/\s+/).group_by{|a| a}.map{|a,b| [a, b.count]}]
  end
end
