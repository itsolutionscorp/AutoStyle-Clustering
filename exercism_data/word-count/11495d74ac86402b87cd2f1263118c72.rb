class Phrase
  def initialize(words)
  	@words = words.downcase.split(/\W+/)
  end

  def word_count
    @counts = @words.each_with_object(Hash.new(0)) do |i, h|
      h[i] += 1
    end
  end

end

#Oh boy is it ugly, but it works.  Please help!
