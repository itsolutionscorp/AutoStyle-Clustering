class Phrase
  def initialize(words)
    @word_list = words.downcase.gsub(/[^a-z0-9 \s\']/, ' ').split(' ')
  end

  def word_count
    count = {}
    for i in 0...@word_list.length
      word = @word_list[i]
      if(count.include?(word))
        count[word] += 1
      else
        count[word] = 1
      end
    end
    return count
  end
end
