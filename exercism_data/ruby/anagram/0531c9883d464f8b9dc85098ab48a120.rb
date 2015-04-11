Anagram = Struct.new :source do
  def match arr
    uniq anagrams arr
  end

  private

  def anagram? arg
    if source.size != arg.size
      false
    else
      to_a( source) == to_a( arg)
    end    
  end

  def uniq arr
    arr.uniq{|x| x.downcase}-[source]
  end

  def anagrams arr
    arr.select{|x| x if anagram?(x)}
  end

  def to_a string
    string.downcase.chars.sort
  end
end
