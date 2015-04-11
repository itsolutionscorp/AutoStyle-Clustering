Anagram = Struct.new :source do
  def match arr
    arr.reject{|x| x unless anagram?(x)}.uniq{|x| x.downcase}-[source]
  end
  
  private
  
  def anagram? arg
    return false if source.size != arg.size
    
    arr1 = to_a( source)
    arr2 = to_a( arg)
    arr1 == arr2
    
  end
  
  def to_a string
    arr=[]
    string.downcase.each_char{|x| arr<<x}
    arr.sort
  end
end
