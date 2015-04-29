class String
  def sort
    list = []
    self.each_char {|e| list << e}
    list.sort!
    list.reduce('', :+)
  end
end

def combine_anagrams(words)
  result = []
  while words.length > 0 do
    word = words[0]
    s = words.select {|w| w.downcase.sort == word.downcase.sort}
    result << s
    words = words.reject {|w| s.include?(w)}
  end
  return result
end


