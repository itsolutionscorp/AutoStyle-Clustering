class String
  def sort
    self.chars.sort
  end
end

def combine_anagrams(words)
  result = {}
  words.each do |x|
    if result.has_key? x.downcase.sort
      result[x.downcase.sort] << x
    else
      result[x.downcase.sort] = [x]
    end
  end
  result.values.sort
end
