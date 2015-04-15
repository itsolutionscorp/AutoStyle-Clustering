require 'byebug'
class Phrase
  def initialize(string)
    @string_array = string.delete(" ")[/\W/] ? self.dividers?(string) : string.split(" ")
    @word_count_hash = Hash.new(0)
    @already_counted = false
  end

  def word_count
    unless @already_counted
      @string_array.each {|word| @word_count_hash[word.downcase] += 1 } 
      @already_counted = true
    end
    @word_count_hash
  end
  
  def dividers?(string)
    if string[/,[\n\ ]/]
      string.delete(",").split("\s")
    elsif string[/,/]
      string.split(",")
    else
      self.word_clean(string)
    end
  end

  def word_clean(string)
    clean_array = string.split(" ").map do |word|
      punctuation = word[/[!&@$%^&:.]+/]
      if punctuation 
        word.delete(punctuation)
      else
        word
      end
    end
    clean_array.delete("")
    clean_array
  end
end
