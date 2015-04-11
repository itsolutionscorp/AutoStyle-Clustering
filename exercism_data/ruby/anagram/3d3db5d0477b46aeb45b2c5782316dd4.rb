class Anagram
  attr_accessor :word, :dictionary

  def initialize(word)
    @word = word
    @dictionary = Hash.new {|hash,key| hash[key] = []}
  end

  def match(candidates)
    candidates.each do |candidate|
      next if candidate.casecmp(word) == 0
      dictionary[index(candidate)] << candidate
    end
    dictionary[index(word)]
  end

  def index(string)
    string.downcase.chars.sort
  end
end
