class Phrase
  def initialize(raw_str)
    @words = raw_str.gsub(/[^a-zA-Z0-9']/, ' ').downcase.split(' ')
  end
  
  def word_count
    list = Hash.new(0)
    @words.each { |word| list[word] += 1 }
    return list
  end
end
