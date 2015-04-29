class String
  def isanagram?(word)
    self.downcase.chars.sort.join.eql?(word.downcase.chars.sort.join)
  end
end

def firstelement(a)
  result = Array.new
  a.each {|el| result.push(el[0])}
  result
end

def combine_anagrams(words)
  result = Array.new
  words.each { |w|
    temp = firstelement result
    root = temp.map { |el| el.isanagram?(w) }
    if i = root.index(true)
      result[i].push(w)
    else 
      result.push([w])
    end
  }
  result
end
