class Anagram
  def initialize(word)
    @word = word.downcase
    @result = Array.new
  end

  def match(array)
    iterate_array(array)
    return @result
  end


  private 

  def iterate_array(array)
    array.each do |field|
      @result << field if !same_word?(field) && anagram?(field)
    end
  end
  
  def same_word?(array_field)
    @word == array_field.downcase
  end

  def anagram?(word)
    sort_chars(@word) == sort_chars(word)
  end

  def sort_chars(word)
    word.downcase.chars.sort.join # changed .split("") to .chars method
  end
end
