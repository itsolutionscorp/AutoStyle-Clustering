class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    filter_by_letters filter_by_checksum valid candidates
  end

  private

  def valid candidates
    candidates - [@word]
  end

  def filter_by_checksum candidates
    sum = @word.sum
    candidates.select {|cand| cand.sum == sum }
  end

  def filter_by_letters candidates
    letters = @word.split(//)
    candidates.select {|cand| (cand.split(//) - letters).empty? }
  end

end
