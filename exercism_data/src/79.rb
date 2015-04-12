class Phrase

  def initialize word
    @word = word.to_s
  end

  def word_count
    @word.gsub!(/[^a-zA-Z0-9']/, " ")

    @words = @word.downcase.split

    counts = Hash.new(0)

    @words.each do |word| 
      counts[word] += 1
    end

    counts
  end

end
