class Phrase
  attr_accessor :string

  def initialize(string)
    self.string = string.downcase.gsub(',',' ').gsub(/[^a-z0-9\s\'']/i, '')
  end

  def word_count
    array = string.split
    no_duplicates = array.uniq
    hash = {}
    no_duplicates.each do |word|
      hash[word] = array.count(word)
    end
    hash
 end
end
