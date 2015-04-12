class Phrase
  @str=""
  def initialize(str)
  @str=str
  end
  def word_count
  word_ary=[]
  word_hash={}
  word_ary=@str.downcase.split(/[^\w']|\s/)
  word_ary=word_ary.delete_if{|item| item=="" }
  word_ary.each{|word|
   word_hash[word] ? word_hash[word]+=1 : word_hash[word]=1
  }
  word_hash
  end
end
