class Phrase
  def initialize(content)
    @content = content.downcase
  end
  
  def word_count
    words.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end
  
  protected
  
  def words
    unpunctuated_content.split(/[[:space:]]+/)
  end
  
  def unpunctuated_content
    @content.gsub(/[^[:alnum:]]+/, ' ')
  end
end
