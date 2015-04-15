class Anagram
  attr_reader :word, :wordarr
  def initialize(word)
    @word = word.downcase
    @wordarr = @word.downcase.split("").sort
  end

  def match(matches)
    solvearr = []
    matches.each do |match|
      if match.downcase.split("").sort == wordarr && match.downcase != word
        solvearr << match
      end
    end
    solvearr
  end
end

detector = Anagram.new('Orchestra')
detector.match(%w(cashregister Carthorse radishes))
