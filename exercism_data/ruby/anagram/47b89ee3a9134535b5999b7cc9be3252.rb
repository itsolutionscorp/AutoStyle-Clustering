 require 'prime'

class Anagram
	def initialize(word)
    @word = word.downcase
    @prime_map ||= alphaprime_map 
    @word_sum = prime_checksum(@word)
  end

  def match(possible_matches)
    matches = []
    possible_matches.each do |match|
      w = match.downcase
      if @word_sum == prime_checksum(w) && (@word.scan /[#{match}]/i).count == match.size
        matches << match unless w == @word
      end
    end
    matches
  end

  def alphaprime_map
    letters = ('a'..'z').to_a
    primes = Prime.first 26
    map = {}
    (0..25).each do |i|
      map[letters[i]] = primes[i]
    end
    map
  end

  def prime_checksum(word)
    word.chars.inject(0) { |sum,c| sum += @prime_map[c]}
  end

end
