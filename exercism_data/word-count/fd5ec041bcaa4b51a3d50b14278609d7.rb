class Phrase
  attr_accessor :word_count
  def initialize(phrase)
    @word_count={}
    words=phrase.scan(/\w[\w\'\-]*/)
    words.each {|word,count|
      if !(@word_count[word.downcase].nil?)
        @word_count[word.downcase]+=1
      else
        @word_count[word.downcase]=1
      end
      }
  end


end
