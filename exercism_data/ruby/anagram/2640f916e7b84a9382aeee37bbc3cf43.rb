class Anagram
  attr_accessor :word

  def initialize(word)
    @word = word
  end

  def match(list)
    result = []
    remove_identicals_in(list).each do |possible_anagram|
      result << possible_anagram if match?(possible_anagram)
    end
    result
  end

  private

    def match?(possible_anagram)
      get_char_map(word) == get_char_map(possible_anagram)
    end

    def remove_identicals_in(list)
      list.each_with_object([]){ |word_in_list, arr| arr << word_in_list unless word_in_list.downcase == word.downcase }
    end

    def get_char_map(word)
      map = Hash.new 0
      word.downcase.split('').each{ |char| map[char] += 1 }
      map
    end
end
