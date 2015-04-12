class Phrase

  attr_reader :text

  def initialize text
    @text = text
  end

  def word_count
    words.each_with_object( Hash.new 0 ) do |word, counts|
      counts[ word ] += 1
    end
  end

private

  SYMBOLS = /[^A-Za-z0-9']/

  def words
    text.downcase.gsub( SYMBOLS, ' ' ).split
  end

end
