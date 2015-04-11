class Phrase

  attr_reader :word_count

  def initialize sentence
    @words = sentence.split(/[\s,:.?!"]+/)
    @words_downcase = @words.map {|word| word.downcase}
    @words_final = @words_downcase.reject {|word| not word.match(/^[a-z0-9]/) }
    @word_count = {}
    @words_final.each {|x| if @word_count[x].nil? then @word_count[x] = 1 else @word_count[x]+=1 end }
  end



end
