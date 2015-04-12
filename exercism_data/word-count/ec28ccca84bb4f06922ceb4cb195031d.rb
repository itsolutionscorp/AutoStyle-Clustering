require 'pry'
class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    result_hash = {}
    regex = @string.downcase.gsub(/[^a-z0-9\s']/i, ' ')
    word_array = regex.split(' ')


    word_array.each do |word|
      if result_hash[word]
        result_hash[word] += 1
      else
        result_hash[word] = 1
      end
    end
    result_hash
  end

end
