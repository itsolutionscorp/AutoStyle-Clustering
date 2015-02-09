#class Anagram
  def combine_anagrams(words)
    hashWords = {}
    words.each do |item|
      inner_array = item.downcase.scan(/[a-z]/)
      str = ""
      inner_array.sort.each do |char|
        str << char
      end
      element = []
      if hashWords[str].nil?
        element << item
        hashWords[str] = element
      else
        element = hashWords[str]
        element << item
      end
    end

    answer = []
    hashWords.collect {
        |key, element|
      answer << element
    }
    answer
  end
#end

#anagram = Anagram.new
output = combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])
puts "#{output}"
output = combine_anagrams([])
puts "#{output}"
