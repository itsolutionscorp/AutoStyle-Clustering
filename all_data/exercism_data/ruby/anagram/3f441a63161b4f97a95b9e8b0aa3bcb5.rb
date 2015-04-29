class Anagram

  def initialize(word)
    @word = word;
  end

  def match(args)
    result = []
    args.each do |w|
      result << w if w.downcase.chars.sort.join == @word.downcase.chars.sort.join && w.downcase != @word.downcase
    end
    result.sort
  end

end
