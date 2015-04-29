class Hash
  def check_for_push(index)
    self[index] = Array.new unless self[index].respond_to?("push")
    self[index]
  end
end  

def combine_anagrams(words)  
  simple = Hash.new
  words.each{|x| simple.check_for_push(x).push x.to_s.downcase.split(//).sort}
  un = Hash.new
  simple.each {|word, codes| codes.each{|cod| un.check_for_push(cod).push(word)}}
  un.values
end