class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    punctuation = [',', '.', '?', '!', '@', '&', '%', '$', '^', ':']

    @string.each_char do |x|
      @string[x] = ' ' if punctuation.include?(x)
    end

    words = @string.downcase.split(' ')
    dictionary = {}

    words.each do |x|
      dictionary[x] = 0 if dictionary[x].nil?
      dictionary[x] += 1
    end
    dictionary
  end
end
