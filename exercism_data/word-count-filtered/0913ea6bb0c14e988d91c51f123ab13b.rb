class Phrase

  def initialize(line)
    @line = line.gsub(',',' ').gsub(/[^a-zA-Z0-9']/, " ").downcase
  end

  def word_count
    word_counts = Hash.new(0)

    words = @line.split(" ")

    words.each do |word|
      word_counts[word] = word_counts[word].next
    end

    word_counts
  end

end
