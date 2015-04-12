class Phrase
  def initialize(words)
  	@words = words.downcase
  	@array = @words.split(/\W+/)
  end

  def word_count
    @array.each do |x|
      @counts = Hash.new { |hash, key| hash[key] += 1 }
      @counts[x]
    end
  end

  private

end

#Oh boy is it ugly, but it works.  Please help!
