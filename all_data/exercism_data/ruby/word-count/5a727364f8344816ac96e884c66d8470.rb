class Phrase
  attr_reader :word_count

  def initialize (ph)
    @ph = ph.downcase
    @word_count = Hash.new(0)
    self.count
  end


  def count
    #array of indices of apostrophe
    index = []
    if @ph.include?"'"
      for i in 0..(@ph.length-1)
        index<<i if @ph[i]=="'"
      end
    end
    newph = @ph.gsub(/\W/,  ' ')
    #put apostrophe in phrase after all punctuations are substituted by blanks
    index.each{|j| newph[j]="'"} if index.length>0
    wordlist = newph.split
    wordlist.each{ |x|
    if @word_count[x]>0
      @word_count[x] += 1
    else
      @word_count[x]=1
    end
    }
  end
end
