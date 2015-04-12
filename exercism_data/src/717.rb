class Phrase

  def initialize(line)
    @words = line.scan(/[\w']+/) 
  end

  def word_count
    word_counts = Hash.new(0)

    #words = @line.split(" ")

    @words.each do |word|
      word_counts[word.downcase] = word_counts[word.downcase].next
    end

    word_counts
  end

end
