class Anagram

  def initialize(word)
    @word = word
  end

  def match(array_of_strings)
    words_array = @word.split("")
    array_of_strings = array_of_strings
    solution_array =[]

    array_of_strings.each do |x|

      if @word == x
        nil
      elsif words_array.sort == x.split("").sort
        solution_array << x
      else
        solution_array
      end
    end
    solution_array
  end
end
