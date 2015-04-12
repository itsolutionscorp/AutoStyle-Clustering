class Phrase
  def initialize(word)
    word = gets.chomp
  end
  def word_count()
    words = word.split(' ')
    counter = Hash.new(0)
    words.each { |word| counter[word.downcase] += 1 }
    counter
  end
end
