class String
  def sort_letters
    self.downcase.chars.sort.join
  end
end

class Array
  def after(obj)
    self[self.index(obj)+1..-1]
  end
end

def combine_anagrams(words)
  combined_anagrams = []
  words.each{ |first_word|
    next if first_word == nil
    anagram = [first_word]
    words.after(first_word).each{ |next_word|
      next if next_word == nil
      if first_word.sort_letters == next_word.sort_letters
        anagram << next_word
        words[words.index(next_word)] = nil
      end
    }
    words[words.index(first_word)] = nil
    combined_anagrams << anagram
  }
  combined_anagrams
end
