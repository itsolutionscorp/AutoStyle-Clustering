class Anagram
  def initialize word
    @source_word = word.to_s.downcase
    @source_chars = @source_word.each_char.select{ |c| true }
  end

  def match word_list
    matches = []

    word_list.each { |word| matches.push(word) if anagram?(word) }

    matches
  end

  private
  
  def anagram? word
    word = word.downcase

    if word.length != @source_word.length || word == @source_word
      false
    else
      available_chars = @source_chars.clone

      word.each_char do |c|
        i = available_chars.index(c) 
        if i
          available_chars.delete_at(i)
        else
          return false
        end
      end

      true
    end
  end
end
