class String
  def palindrome?
    self.plain == self.reverse.plain
  end

  def plain
    self.downcase.gsub(/\W/, '')
  end

  def count_words
    words = Hash.new
    self.downcase.split(/\b/).each do |w|
      if  /\w+/ =~ w
        if words[w] == nil
          words[w] = 1
        else
          words[w] = words[w] + 1
        end
      end
    end
    words
  end
end

def palindrome?(phrase)
  phrase.palindrome?
end

def count_words(phrase)
  phrase.count_words
end

def combine_anagrams(words)
  words_hash = words.group_by {|word| word.downcase.chars.sort}
  words_hash.values
end