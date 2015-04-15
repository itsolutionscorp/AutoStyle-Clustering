class Anagram
  def initialize(word)
    @word1 = word.downcase
  end

  def match(list)
    count = Hash.new(0)
    @word1.each_char { |letter| count[letter] += 1 }
    result = []
    until list.empty? == true 
      return_word = list.last
      word2 = list.pop.downcase
      count2 = Hash.new(0)
      word2.each_char { |letter| count2[letter] += 1 }
      if count2 == count
        if @word1.chars.zip(word2.chars).count { |c1, c2| c1 != c2 } > 0
          result << return_word
        end
      end
    end
     result
  end
end
