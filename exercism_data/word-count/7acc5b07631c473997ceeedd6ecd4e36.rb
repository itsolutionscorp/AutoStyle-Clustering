class Phrase
  def initialize(string)
    @string = sanitize(string)
  end

  def word_count
    count = Hash.new(0)

    words_in_string.each do |word|
      count[word.downcase] += 1
    end

    count
  end

  private

  def sanitize(string)
    string.gsub(/[^\w|\s,]/, '')
  end

  def words_in_string
    @string.gsub(/,/, " ").split(" ")
  end
end
