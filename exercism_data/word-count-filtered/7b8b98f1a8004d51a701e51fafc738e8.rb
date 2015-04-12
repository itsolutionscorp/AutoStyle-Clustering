class Phrase
  def initialize(s)
    @input = s
  end

  def word_count
    words = Hash.new 
    @input.downcase.scan(/[\w']+/) do |i|
      if words.has_key?(i) 
        words[i] += 1
      else
        words[i] = 1
      end
    end

    return words
  end
end
