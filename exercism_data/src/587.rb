class Phrase
  def initialize(words)
  	@words = words.downcase.gsub /\W+/, ' '
  	@array = @words.split(/ /)
  end

  def word_count
  	@counts = Hash.new
  	@array.each do |w|
  	  unless @counts.has_key?(w)
  	    @counts[w] = 1
  	  else
        @counts[w] += 1
      end
  	end
    @counts
  end
end

#Oh boy is it ugly, but it works.  Please help!
