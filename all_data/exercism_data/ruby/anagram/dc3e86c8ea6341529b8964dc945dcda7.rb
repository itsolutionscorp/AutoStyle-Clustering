class Anagram
  def initialize(detector)
    @detector = detector
    @array = @detector.downcase.split(//)
    @array.sort!
  end

  def match(anagrams)

  	@anagrams = anagrams
  	@stuff = Hash.new
  	@anagrams.each do |a|
  	  @stuff[a] = a.downcase.split(//).sort
  	end

  	@matches = Array.new
  	@stuff.each do |key, value|
  	  if value == @array
  	  	unless key.downcase == @detector
  	  	  @matches << key
  	  	end
  	  end
  	end

  	return @matches
  end
end
