class Phrase
  def initialize(input)
    @paragraph=input
  end
  def word_count
    words_split= @paragraph.scan(/\w+/)
    word_repeat=Hash.new(0)
    words_split.each{|a|
      if word_repeat.has_key?(a.downcase)
        word_repeat[a.downcase] += 1
      else
        word_repeat[a.downcase]=1
      end }
  word_repeat 
  end
end

#test=Phrase.new("go Go go Go")
#test.word_count
