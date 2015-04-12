class Phrase
  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def word_count
    count(words)
  end

  private

  def words
    @sentence.scan(/\w+/)
  end

  def count(words)
    words.inject(Hash.new(0)) { |count, word| count[word.downcase] += 1; count }
  end

  #def word_count_compact
    #@sentence.scan(/\w+/).inject(Hash.new(0)) { |count, word| count[word.downcase] += 1; count }
  #end

  #def word_count_scanning
    #count = Hash.new(0)
    #@sentence.scan(/\w+/) { |word| count[word.downcase] += 1 }
    #count
  #end

  #def word_count_splitting
    #words = @sentence.split(/\W/).delete_if { |word| word.to_s.strip.empty? }
    #words.inject(Hash.new(0)) { |count, word| count[word.downcase] += 1; count }
  #end
end
