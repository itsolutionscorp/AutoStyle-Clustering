class Anagram
  def initialize(string)
    @string = string
  end
  def match(match_array)
    return_array = []
    match_array.each do |word|
      if word.downcase.scan(/\w/).sort == @string.downcase.scan(/\w/).sort && word.downcase != @string.downcase
        return_array << word
      end
    end
    return_array
  end
end
