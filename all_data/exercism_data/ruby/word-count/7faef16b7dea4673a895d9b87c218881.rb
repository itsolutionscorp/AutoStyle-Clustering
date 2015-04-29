class Phrase

  attr_reader :sentence

  def initialize sentence
    @sentence = sentence
  end

  def word_count
    words.each_with_object Hash.new( 0 ) do |word, counts|
      counts[ word.downcase ] += 1
    end
  end

private

  def sanitized_sentence
    sentence.gsub( /[^'a-zA-Z\d*]/, " ")
  end

  def exclude_line_break
    sanitized_sentence.strip
  end

  def words
    exclude_line_break.split
  end

end
