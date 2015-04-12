class Phrase
  def initialize(input)
    @paragraph=input
  end
  def word_count
    words_split= @paragraph.scan(/\w+/).downcase
    word_length=words_split.length
    i=0
    word_repeat=Hash.new
    until i >= word_length
      if word_repeat.has_key?(words_split[i])
        word_repeat[words_split[i]] += 1
      else
        word_repeat[words_split[i]]=1
      end	     
      i+=1
    end
  word_repeat 
  end
end

#test=Phrase.new("go Go go Go")
#test.word_count
