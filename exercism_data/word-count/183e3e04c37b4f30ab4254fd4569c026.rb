class Phrase

  WORD_BREAK_CHARACTERS = /[^\w']/

  def initialize word
    @words = word.split WORD_BREAK_CHARACTERS
    @words.delete_if &:empty?
  end

  def word_count
    count = Hash.new 0
    @words.each do |word|
      count[word.downcase] += 1
    end
    count
  end

end
