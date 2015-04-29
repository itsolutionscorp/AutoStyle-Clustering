class Anagram 

  def initialize args
    @word = args
  end

  def match args
    @anagrams = args
    check_for_mathches
    @results
  end

  private

  def check_for_mathches
    @results = []
    @anagrams.each do |anagram|
      if anagram.downcase.scan(/./).sort.eql? @word.downcase.scan(/./).sort
        @results << anagram unless (anagram.downcase == @word.downcase)
      end
    end
  end

end
